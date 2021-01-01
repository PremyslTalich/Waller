package eu.talich.waller.presentation.photos.vm

import androidx.lifecycle.ViewModel
import eu.talich.domain.usecase.GetPhotosUseCase
import eu.talich.domain.usecase.GetSearchQueryUseCase
import eu.talich.domain.usecase.SearchPhotosUseCase
import eu.talich.waller.R
import eu.talich.waller.presentation.common.adapter.ClearAdapter
import eu.talich.waller.presentation.common.mapper.PhotoMapper
import eu.talich.waller.presentation.common.model.PhotoVo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val getSearchQueryUseCase: GetSearchQueryUseCase,
    private val photoMapper: PhotoMapper,
    private val clearAdapter: ClearAdapter
): ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val _photos = MutableStateFlow<List<PhotoVo>>(listOf())
    val photos: StateFlow<List<PhotoVo>> = _photos

    private val _state = MutableStateFlow<ViewState>(Init)
    val state: StateFlow<ViewState> = _state

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMorePhotos()

        launch {
            getSearchQueryUseCase().collect {
                clearAdapter.clearAdapter()

                page = 1
                searchQuery = it
                _photos.value = emptyList()
                loadMorePhotos()
            }
        }
    }

    fun loadMorePhotos() {
        launch(Dispatchers.IO) {
            val newPhotos =
                searchQuery?.let {
                    searchPhotosUseCase(it, page).results.map { photo ->
                        photoMapper.map(photo)
                    }
                } ?: run {
                    getPhotosUseCase(page).map {
                        photoMapper.map(it)
                    }
                }

            if (newPhotos.isNotEmpty()) {
                _photos.value = newPhotos
                _state.value = HasPhotos
                page++
            } else {
                if (searchQuery != null && page == 1) {
                    _state.value = EmptySearch(R.drawable.ic_heart, R.string.no_photos_found)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
