package eu.talich.waller.feature.photodetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.common.navigation.model.Main
import eu.talich.waller.common.navigation.model.PhotoDetail
import eu.talich.waller.library.navigation.domain.NavigateUseCase
import eu.talich.waller.library.search.domain.SetSearchQueryUseCase
import eu.talich.waller.library.unsplash.domain.GetPhotoDetailUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class PhotoDetailViewModel(
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    private val setSearchQueryUseCase: SetSearchQueryUseCase,
    private val navigateUseCase: NavigateUseCase,
    val photo: PhotoDetail
): ViewModel() {

    private val _photoDetail = MutableStateFlow<PhotoDetailVo?>(null)
    val photoDetail: StateFlow<PhotoDetailVo?> = _photoDetail

    init {
        viewModelScope.launch {
            loadPhotoDetail(photo.id)
        }
    }

    private suspend fun loadPhotoDetail(photoId: String) {
        _photoDetail.value = getPhotoDetailUseCase(photoId).toPhotoDetailVo()
    }

    fun onTagClicked(tagId: String) {
        viewModelScope.launch {
            setSearchQueryUseCase(tagId)
            navigateUseCase(Main)
        }
    }
}