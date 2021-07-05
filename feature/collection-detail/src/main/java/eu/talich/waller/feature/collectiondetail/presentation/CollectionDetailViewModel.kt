package eu.talich.waller.feature.collectiondetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.common.navigation.model.CollectionDetail
import eu.talich.waller.common.navigation.model.PhotoDetail
import eu.talich.waller.common.navigation.model.WallerRouterDestinations
import eu.talich.waller.library.internetobserver.domain.ObserveInternetConnectionUseCase
import eu.talich.waller.library.navigation.domain.NavigateUseCase
import eu.talich.waller.library.unsplash.domain.GetCollectionPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CollectionDetailViewModel(
    private val getCollectionPhotosUseCase: GetCollectionPhotosUseCase,
    private val observeInternetConnectionUseCase: ObserveInternetConnectionUseCase,
    private val navigateUseCase: NavigateUseCase,
    val collection: CollectionDetail
): ViewModel() {
    private val _photos = MutableStateFlow<List<PhotoVo>>(listOf())
    val photos: StateFlow<List<PhotoVo>> = _photos

    private val _alertState = MutableStateFlow<AlertState>(None)
    val alertState: StateFlow<AlertState> = _alertState

    private val _loadingBarState = MutableStateFlow<Boolean>(false)
    val loadingBarState: StateFlow<Boolean> = _loadingBarState

    var page: Int = 1
    var hasTransitionEnded: Boolean = false

    init {
        loadMoreCollectionPhotos()

        viewModelScope.launch {
            observeInternetConnectionUseCase().collect { hasInternetConnection ->
                if (hasInternetConnection) {
                    _alertState.value = None
                } else {
                    _alertState.value = NoInternet
                }
            }
        }
    }

    fun loadMoreCollectionPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingBarState.value = true

            try {
                val newPhotos = getCollectionPhotosUseCase(collection.id, page).map {
                    it.toPhotoVo()
                }.toMutableList()

                if (page == 1) {
                    newPhotos.removeFirst()
                }

                if (newPhotos.isNotEmpty()) {
                    _photos.value = newPhotos
                    _alertState.value = None
                    page++
                }
            } catch (e: Exception) {
                _alertState.value = BadConnection
            }

            _loadingBarState.value = false
        }
    }

    fun navigateToPhotoDetail(id: String, url: String, color: String) {
        viewModelScope.launch {
            navigateUseCase(
                PhotoDetail(
                    id = id,
                    url = url,
                    color = color,
                    blurHash = null
                )
            )
        }
    }
}
