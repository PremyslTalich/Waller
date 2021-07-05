package eu.talich.waller.feature.photos.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.common.ui.system.compose.AlertRibbon
import eu.talich.waller.common.ui.system.compose.BackgroundAlert
import eu.talich.waller.common.ui.system.compose.LoadingBar
import eu.talich.waller.feature.photos.R
import eu.talich.waller.feature.photos.presentation.PhotosViewModel
import eu.talich.waller.feature.photos.presentation.PhotosViewState
import eu.talich.waller.feature.photos.system.compose.PhotoCard
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment(), MainScreenPage {

    override val title: Int
        get() = R.string.photos_title

    override val weight: Int
        get() = 10

    private val viewModel by viewModel<PhotosViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val viewState = viewModel.viewState.value
                val photosLastIndex = viewState.photos.lastIndex

                val lazyListState: LazyListState = rememberLazyListState()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (viewState.photos.isNotEmpty()) {
                        LazyColumn(
                            state = lazyListState,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            itemsIndexed(viewState.photos) { index, photo ->
                                PhotoCard(photo, viewModel::navigateToPhotoDetail)

                                if (index == photosLastIndex && !viewState.loading) {
                                    LaunchedEffect(Unit) {
                                        viewModel.loadMorePhotos()
                                    }
                                }
                            }
                        }
                    }

                    when(viewState.alert) {
                        PhotosViewState.AlertState.EMPTY_SEARCH -> {
                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                BackgroundAlert(
                                    R.drawable.ic_search_off,
                                    R.string.no_photos_found
                                )
                            }
                        }
                        PhotosViewState.AlertState.BAD_CONNECTION -> {
                            Surface(
                                modifier = Modifier.align(Alignment.BottomCenter)
                            ) {
                                AlertRibbon(getString(R.string.bad_unsplash_connection))
                            }
                        }
                        PhotosViewState.AlertState.NO_INTERNET -> {
                            Surface(
                                modifier = Modifier.align(Alignment.BottomCenter)
                            ) {
                                AlertRibbon(getString(R.string.no_internet))
                            }
                        }
                    }

                    if (viewState.loading) {
                        Surface(
                            modifier = Modifier.align(Alignment.BottomCenter)
                        ) {
                            LoadingBar()
                        }
                    }
                }
            }
        }
    }
}
