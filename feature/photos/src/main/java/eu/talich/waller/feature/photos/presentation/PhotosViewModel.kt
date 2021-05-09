package eu.talich.waller.feature.photos.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.common.navigation.model.WallerRouterDestinations
import eu.talich.waller.feature.photos.model.PhotoVo
import eu.talich.waller.feature.photos.model.mapper.UnsplashPhotoMapper
import eu.talich.waller.library.internetobserver.domain.ObserveInternetConnectionUseCase
import eu.talich.waller.library.navigation.domain.NavigateUseCase
import eu.talich.waller.library.search.domain.ObserveSearchQueryUseCase
import eu.talich.waller.library.unsplash.domain.GetPhotosUseCase
import eu.talich.waller.library.unsplash.domain.SearchPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val observeSearchQueryUseCase: ObserveSearchQueryUseCase,
    private val observeInternetConnectionUseCase: ObserveInternetConnectionUseCase,
    private val unsplashPhotoMapper: UnsplashPhotoMapper,
    private val navigateUseCase: NavigateUseCase
): ViewModel() {

    val loadingState: MutableState<Boolean> = mutableStateOf(false)
    val photos: MutableState<List<PhotoVo>> = mutableStateOf(emptyList())
    val alertState: MutableState<AlertState> = mutableStateOf(None)

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMorePhotos()

        viewModelScope.launch(Dispatchers.IO) {
            observeSearchQueryUseCase().collect {
                page = 1
                searchQuery = it
                photos.value = emptyList()
                loadMorePhotos()
            }

            observeInternetConnectionUseCase().collect { hasInternetConnection ->
                if (hasInternetConnection) {
                    alertState.value = None
                } else {
                    alertState.value = NoInternet
                }
            }
        }
    }

    fun loadMorePhotos() {
        loadingState.value = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newPhotos = searchQuery?.let {
                    searchPhotosUseCase(it, page).results.map { photo ->
                        unsplashPhotoMapper.map(photo)
                    }
                } ?: run {
                    getPhotosUseCase(page).map {
                        unsplashPhotoMapper.map(it)
                    }
                }

                if (newPhotos.isNotEmpty()) {
                    photos.value += newPhotos
                    page++
                    alertState.value = None
                } else {
                    if (searchQuery != null && page == 1) {
                        alertState.value = EmptySearch
                    }
                }
            } catch (e: Exception) {
                println("Exception = $e")

                if (alertState.value != NoInternet) {
                    alertState.value = BadConnection
                }
            }

            loadingState.value = false
        }
    }

    fun navigateToPhotoDetail(photo: PhotoVo) {
        viewModelScope.launch {
            navigateUseCase(
                WallerRouterDestinations.PhotoDetail(
                    photo.id,
                    photo.urlFull,
                    photo.color,
                    photo.blurHash?.let {
                        WallerRouterDestinations.PhotoDetail.BlurHash(
                            it.hash,
                            it.width,
                            it.height
                        )
                    }
                )
            )
        }
    }
}
