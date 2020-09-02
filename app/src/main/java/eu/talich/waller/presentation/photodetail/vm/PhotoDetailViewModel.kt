package eu.talich.waller.presentation.photodetail.vm

import androidx.lifecycle.ViewModel
import eu.talich.domain.usecase.GetPhotoDetailUseCase
import eu.talich.waller.presentation.common.mapper.PhotoDetailMapper
import eu.talich.waller.presentation.common.model.PhotoDetailVo
import eu.talich.waller.presentation.common.model.PhotoVo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class PhotoDetailViewModel(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    private val photoDetailMapper: PhotoDetailMapper,
    val photo: PhotoVo
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _photoDetail = MutableStateFlow<PhotoDetailVo?>(null)
    val photoDetail: StateFlow<PhotoDetailVo?> = _photoDetail

    init {
        launch {
            loadPhotoDetail(photo.id)
        }
    }

    private suspend fun loadPhotoDetail(photoId: String) {
        _photoDetail.value = photoDetailMapper.map(
            getPhotoDetailUseCase(photoId)
        )
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}