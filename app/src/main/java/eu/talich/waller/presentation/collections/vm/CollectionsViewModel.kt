package eu.talich.waller.presentation.collections.vm

import androidx.lifecycle.ViewModel
import eu.talich.domain.usecase.GetFeaturedCollectionsUseCase
import eu.talich.domain.usecase.GetSearchQueryUseCase
import eu.talich.domain.usecase.SearchCollectionsUseCase
import eu.talich.waller.presentation.common.adapter.ClearAdapter
import eu.talich.waller.presentation.common.mapper.CollectionMapper
import eu.talich.waller.presentation.common.model.CollectionVo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CollectionsViewModel(
    private val getFeaturedCollectionsUseCase: GetFeaturedCollectionsUseCase,
    private val searchCollectionsUseCase: SearchCollectionsUseCase,
    private val collectionMapper: CollectionMapper,
    private val getSearchQueryUseCase: GetSearchQueryUseCase,
    private val clearAdapter: ClearAdapter
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _collections = MutableStateFlow<List<CollectionVo>>(listOf())
    val collections: StateFlow<List<CollectionVo>> = _collections

    private val _state = MutableStateFlow<ViewState>(Init)
    val state: StateFlow<ViewState> = _state

    private val _loadingBarState = MutableStateFlow<Boolean>(false)
    val loadingBarState: StateFlow<Boolean> = _loadingBarState

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMoreCollections()

        launch {
            getSearchQueryUseCase().collect {
                clearAdapter.clearAdapter()

                page = 1
                searchQuery = it
                _collections.value = emptyList()
                loadMoreCollections()
            }
        }
    }

    fun loadMoreCollections() {
        launch(Dispatchers.IO) {
            _loadingBarState.value = true

            try {
                val newCollections =
                    searchQuery?.let {
                        searchCollectionsUseCase(it, page).results.map { photo ->
                            collectionMapper.map(photo)
                        }
                    } ?: run {
                        getFeaturedCollectionsUseCase(page).map {
                            collectionMapper.map(it)
                        }
                    }

                if (newCollections.isNotEmpty()) {
                    _collections.value = newCollections
                    _state.value = HasCollections
                    page++
                } else {
                    if (searchQuery != null && page == 1) {
                        _state.value = EmptySearch
                    }
                }
            } catch (e: Exception) {
                _state.value = BadConnection
            }

            _loadingBarState.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}