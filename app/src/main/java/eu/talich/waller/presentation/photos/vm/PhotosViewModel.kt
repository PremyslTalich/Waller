package eu.talich.waller.presentation.photos.vm

import androidx.lifecycle.ViewModel
import eu.talich.domain.usecase.GetPhotosUseCase
import eu.talich.waller.presentation.common.mapper.PhotoMapper
import eu.talich.waller.presentation.common.model.PhotoVo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val photoMapper: PhotoMapper
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _photos = MutableStateFlow<List<PhotoVo>>(listOf())
    val photos: StateFlow<List<PhotoVo>> = _photos

    private var page: Int = 1

    init {
        loadMorePhotos()
    }

    fun loadMorePhotos() {
        launch(Dispatchers.IO) {
            val newPhotos = getPhotosUseCase(page).map {
                photoMapper.map(it)
            }

            if (newPhotos.isNotEmpty()) {
                _photos.value = newPhotos
                page++
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}