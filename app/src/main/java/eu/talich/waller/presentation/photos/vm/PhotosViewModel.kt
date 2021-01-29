package eu.talich.waller.presentation.photos.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.domain.usecase.GetPhotosUseCase
import eu.talich.domain.usecase.GetSearchQueryUseCase
import eu.talich.domain.usecase.ObserveInternetConnectionUseCase
import eu.talich.domain.usecase.SearchPhotosUseCase
import eu.talich.waller.presentation.common.mapper.PhotoMapper
import eu.talich.waller.presentation.common.model.PhotoVo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val getSearchQueryUseCase: GetSearchQueryUseCase,
    private val observeInternetConnectionUseCase: ObserveInternetConnectionUseCase,
    private val photoMapper: PhotoMapper
): ViewModel() {

    val loadingState: MutableState<Boolean> = mutableStateOf(false)
    val photos: MutableState<List<PhotoVo>> = mutableStateOf(emptyList())
    val alertState: MutableState<AlertState> = mutableStateOf(None)

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMorePhotos()

        viewModelScope.launch(Dispatchers.IO) {
            getSearchQueryUseCase().collect {
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
                        photoMapper.map(photo)
                    }
                } ?: run {
                    getPhotosUseCase(page).map {
                        photoMapper.map(it)
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
}
