package eu.talich.waller.presentation.collectiondetail.vm

import androidx.lifecycle.ViewModel
import eu.talich.domain.usecase.GetCollectionPhotosUseCase
import eu.talich.waller.presentation.common.model.CollectionVo
import eu.talich.waller.presentation.common.model.PhotoVo
import eu.talich.waller.presentation.common.mapper.PhotoMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CollectionDetailViewModel(
    private val getCollectionPhotosUseCase: GetCollectionPhotosUseCase,
    val collection: CollectionVo,
    private val photoMapper: PhotoMapper
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _photos = MutableStateFlow<List<PhotoVo>>(listOf())
    val photos: StateFlow<List<PhotoVo>> = _photos

    var page: Int = 0

    init {
        loadMoreCollectionPhotos()
    }

    fun loadMoreCollectionPhotos() {
        page++

        launch(Dispatchers.IO) {
            _photos.value = getCollectionPhotosUseCase(collection.id, page).map {
                photoMapper.map(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}