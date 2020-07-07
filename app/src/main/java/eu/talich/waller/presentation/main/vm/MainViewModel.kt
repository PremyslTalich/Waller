package eu.talich.waller.presentation.main.vm

import androidx.lifecycle.ViewModel
import eu.talich.domain.usecase.GetImagesUseCase
import eu.talich.waller.presentation.main.model.ImageVo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MainViewModel(
    private val getImagesUseCase: GetImagesUseCase
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _images = MutableStateFlow<List<ImageVo>>(listOf())
    val images: StateFlow<List<ImageVo>> = _images

    var page: Int = 0

    init {
        loadMoreImages()
    }

    fun loadMoreImages() {
        page++

        launch(Dispatchers.IO) {
            _images.value = getImagesUseCase(page).map {
                ImageVo(
                    it.id,
                    it.description,
                    it.width,
                    it.height,
                    it.urls?.small,
                    it.urls?.regular
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}