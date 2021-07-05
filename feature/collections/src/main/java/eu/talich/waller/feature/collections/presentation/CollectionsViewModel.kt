package eu.talich.waller.feature.collections.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.common.navigation.model.CollectionDetail
import eu.talich.waller.common.navigation.model.WallerRouterDestinations
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

class CollectionsViewModel(
    private val getFeaturedCollectionsUseCase: GetFeaturedCollectionsUseCase,
    private val searchCollectionsUseCase: SearchCollectionsUseCase,
    private val observeSearchQueryUseCase: ObserveSearchQueryUseCase,
    private val observeInternetConnectionUseCase: ObserveInternetConnectionUseCase,
    private val navigateUseCase: NavigateUseCase,
    private val onCollectionsCleared: () -> Unit
): ViewModel() {
    private val _collections = MutableStateFlow<List<CollectionVo>>(listOf())
    val collections: StateFlow<List<CollectionVo>> = _collections

    private val _alertState = MutableStateFlow<AlertState>(None)
    val alertState: StateFlow<AlertState> = _alertState

    private val _loadingBarState = MutableStateFlow<Boolean>(false)
    val loadingBarState: StateFlow<Boolean> = _loadingBarState

    private var page: Int = 1
    private var searchQuery: String? = null

    init {
        loadMoreCollections()

        viewModelScope.launch {
            observeSearchQueryUseCase().collect {
                onCollectionsCleared()

                page = 1
                searchQuery = it
                _collections.value = emptyList()
                loadMoreCollections()
            }
        }

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

    fun loadMoreCollections() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingBarState.value = true

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
                    _collections.value = newCollections
                    page++
                } else {
                    if (searchQuery != null && page == 1) {
                        _alertState.value = EmptySearch
                    }
                }
            } catch (e: Exception) {
                _alertState.value = BadConnection
            }

            _loadingBarState.value = false
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
