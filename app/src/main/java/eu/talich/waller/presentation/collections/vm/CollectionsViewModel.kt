package eu.talich.waller.presentation.collections.vm

import androidx.lifecycle.ViewModel
import eu.talich.domain.usecase.GetFeaturedCollectionsUseCase
import eu.talich.waller.presentation.common.mapper.CollectionMapper
import eu.talich.waller.presentation.common.model.CollectionVo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CollectionsViewModel(
    private val getFeaturedCollectionsUseCase: GetFeaturedCollectionsUseCase,
    private val collectionMapper: CollectionMapper
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _collections = MutableStateFlow<List<CollectionVo>>(listOf())
    val collections: StateFlow<List<CollectionVo>> = _collections

    private var page: Int = 0

    init {
        loadMoreCollections()
    }

    fun loadMoreCollections() {
        page++

        launch(Dispatchers.IO) {
            _collections.value = getFeaturedCollectionsUseCase(page).map {
                collectionMapper.map(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}