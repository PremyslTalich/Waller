package eu.talich.waller.feature.collections.presentation

import androidx.lifecycle.ViewModel
import eu.talich.waller.feature.collections.model.mapper.UnsplashCollectionMapper
import eu.talich.waller.library.internetobserver.domain.ObserveInternetConnectionUseCase
import eu.talich.waller.library.search.domain.ObserveSearchQueryUseCase
import eu.talich.waller.library.unsplash.domain.GetFeaturedCollectionsUseCase
import eu.talich.waller.library.unsplash.domain.SearchCollectionsUseCase
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
    private val unsplashCollectionMapper: UnsplashCollectionMapper,
    private val observeSearchQueryUseCase: ObserveSearchQueryUseCase,
    private val observeInternetConnectionUseCase: ObserveInternetConnectionUseCase,
    private val onCollectionsCleared: () -> Unit
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _collections = MutableStateFlow<List<CollectionVo>>(listOf())
    val collections: StateFlow<List<CollectionVo>> = _collections

    private val _alertState = MutableStateFlow<AlertState>(None)
    val alertState: StateFlow<AlertState> = _alertState

    private val _loadingBarState = MutableStateFlow<Boolean>(false)
    val loadingBarState: StateFlow<Boolean> = _loadingBarState

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMoreCollections()

        launch {
            getSearchQueryUseCase().collect {
                onCollectionsCleared()

                page = 1
                searchQuery = it
                _collections.value = emptyList()
                loadMoreCollections()
            }
        }

        launch {
            observeInternetConnectionUseCase().collect { hasInternetConnection ->
                if (hasInternetConnection) {
                    _alertState.value = None
                } else {
                    _alertState.value = NoInternet
                }
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
                    page++
                } else {
                    if (searchQuery != null && page == 1) {
                        _alertState.value = EmptySearch
                    }
                }
            } catch (e: Exception) {
                _alertState.value = BadConnection
            }

            _loadingBarState.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}