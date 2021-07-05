package eu.talich.waller.feature.photos.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.common.navigation.model.PhotoDetail
import eu.talich.waller.feature.photos.model.PhotoVo
import eu.talich.waller.library.internetobserver.domain.ObserveInternetConnectionUseCase
import eu.talich.waller.library.navigation.domain.NavigateUseCase
import eu.talich.waller.library.search.domain.ObserveSearchQueryUseCase
import eu.talich.waller.library.unsplash.domain.GetPhotosUseCase
import eu.talich.waller.library.unsplash.domain.SearchPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val observeSearchQueryUseCase: ObserveSearchQueryUseCase,
    private val observeInternetConnectionUseCase: ObserveInternetConnectionUseCase,
    private val navigateUseCase: NavigateUseCase
): ViewModel() {
    val viewState: MutableState<PhotosViewState> = mutableStateOf(PhotosViewState())

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMorePhotos()

        viewModelScope.launch(Dispatchers.IO) {
            observeSearchQueryUseCase().collect {
                page = 1
                searchQuery = it

                withContext(Dispatchers.Main) {
                    viewState.value = viewState.value.copy(
                        photos = emptyList()
                    )
                }

                loadMorePhotos()
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            observeInternetConnectionUseCase().collect { hasInternetConnection ->
                withContext(Dispatchers.Main) {
                    viewState.value = viewState.value.copy(
                        alert = if (!hasInternetConnection) {
                            PhotosViewState.AlertState.NO_INTERNET
                        } else {
                            null
                        }
                    )
                }
            }
        }
    }

    fun loadMorePhotos() {
        viewState.value = viewState.value.copy(
            loading = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newPhotos = searchQuery?.let {
                    searchPhotosUseCase(it, page).results.map { photo ->
                        photo.toPhotoVo()
                    }
                } ?: run {
                    getPhotosUseCase(page).map { photo ->
                        photo.toPhotoVo()
                    }
                }

                if (newPhotos.isNotEmpty()) {
                    viewState.value = viewState.value.copy(
                        photos = viewState.value.photos + newPhotos
                    )
                    page++

                    viewState.value = viewState.value.copy(
                        alert = null
                    )
                } else {
                    if (searchQuery != null && page == 1) {
                        viewState.value = viewState.value.copy(
                            alert = PhotosViewState.AlertState.EMPTY_SEARCH
                        )
                    }
                }
            } catch (e: Exception) {
                println("Exception = $e")

                if (viewState.value.alert != PhotosViewState.AlertState.NO_INTERNET) {
                    viewState.value = viewState.value.copy(
                        alert = PhotosViewState.AlertState.BAD_CONNECTION
                    )
                }
            }

            viewState.value = viewState.value.copy(
                loading = false
            )
        }
    }

    fun navigateToPhotoDetail(photo: PhotoVo) {
        viewModelScope.launch {
            navigateUseCase(
                PhotoDetail(
                    photo.id,
                    photo.urlFull,
                    photo.color,
                    photo.blurHash?.let {
                        PhotoDetail.BlurHash(
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
