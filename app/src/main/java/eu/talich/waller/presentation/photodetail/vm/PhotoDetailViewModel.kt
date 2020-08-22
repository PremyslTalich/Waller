package eu.talich.waller.presentation.photodetail.vm

import androidx.lifecycle.ViewModel
import eu.talich.waller.presentation.common.model.PhotoVo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class PhotoDetailViewModel(
    val photo: PhotoVo
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    init {

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}