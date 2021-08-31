package eu.talich.waller.feature.collections.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.common.navigation.model.CollectionDetail
import eu.talich.waller.library.internetobserver.domain.ObserveInternetConnectionUseCase
import eu.talich.waller.library.navigation.domain.NavigateUseCase
import eu.talich.waller.library.search.domain.ObserveSearchQueryUseCase
import eu.talich.waller.library.unsplash.domain.GetFeaturedCollectionsUseCase
import eu.talich.waller.library.unsplash.domain.SearchCollectionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectionsViewModel(
    private val getFeaturedCollectionsUseCase: GetFeaturedCollectionsUseCase,
    private val searchCollectionsUseCase: SearchCollectionsUseCase,
    private val observeSearchQueryUseCase: ObserveSearchQueryUseCase,
    private val observeInternetConnectionUseCase: ObserveInternetConnectionUseCase,
    private val navigateUseCase: NavigateUseCase
): ViewModel() {
    private val _viewState = MutableStateFlow<CollectionsViewState>(CollectionsViewState())
    val viewState: StateFlow<CollectionsViewState> = _viewState

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMoreCollections()

        viewModelScope.launch {
            observeSearchQueryUseCase().collect { newSearchQuery ->
                newSearchQuery?.let {
                    page = 1
                    searchQuery = it

                    withContext(Dispatchers.Main) {
                        _viewState.value = _viewState.value.copy(
                            collections = emptyList()
                        )
                    }

                    loadMoreCollections()
                }
            }
        }

        viewModelScope.launch {
            observeInternetConnectionUseCase().collect { hasInternetConnection ->
                withContext(Dispatchers.Main) {
                    _viewState.value = _viewState.value.copy(
                        alert = if (!hasInternetConnection) {
                            CollectionsViewState.AlertState.NO_INTERNET
                        } else {
                            null
                        }
                    )
                }
            }
        }
    }

    fun loadMoreCollections() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = _viewState.value.copy(
                loading = true
            )

            try {
                val newCollections =
                    searchQuery?.let {
                        searchCollectionsUseCase(it, page).results.map { collection ->
                            collection.toCollectionVo()
                        }
                    } ?: run {
                        getFeaturedCollectionsUseCase(page).map { collection ->
                            collection.toCollectionVo()
                        }
                    }

                if (newCollections.isNotEmpty()) {
                    _viewState.value = _viewState.value.copy(
                        collections = _viewState.value.collections + newCollections
                    )

                    page++
                } else {
                    if (searchQuery != null && page == 1) {
                        _viewState.value = _viewState.value.copy(
                            alert = CollectionsViewState.AlertState.EMPTY_SEARCH
                        )
                    }
                }
            } catch (e: Exception) {
                if (_viewState.value.alert != CollectionsViewState.AlertState.NO_INTERNET) {
                    _viewState.value = _viewState.value.copy(
                        alert = CollectionsViewState.AlertState.BAD_CONNECTION
                    )
                }
            }

            _viewState.value = _viewState.value.copy(
                loading = false
            )
        }
    }

    fun navigateToCollectionDetail(
        id: String,
        title: String,
        description: String,
        coverPhotoId: String,
        coverPhotoUrl: String,
        coverPhotoThumbnailUrl: String,
        coverPhotoColor: String
    ) {
        viewModelScope.launch {
            navigateUseCase(
                CollectionDetail(
                    id,
                    title,
                    description,
                    CollectionDetail.CoverPhoto(
                        coverPhotoId,
                        coverPhotoUrl,
                        coverPhotoThumbnailUrl,
                        coverPhotoColor
                    )
                )
            )
        }
    }
}
