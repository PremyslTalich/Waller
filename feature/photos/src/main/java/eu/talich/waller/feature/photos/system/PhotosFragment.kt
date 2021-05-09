package eu.talich.waller.feature.photos.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.onActive
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import eu.talich.waller.common.ui.system.compose.AlertRibbon
import eu.talich.waller.common.ui.system.compose.BackgroundAlert
import eu.talich.waller.common.ui.system.compose.LoadingBar
import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.feature.photos.R
import eu.talich.waller.feature.photos.model.PhotoVo
import eu.talich.waller.feature.photos.presentation.*
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
                val photos = viewModel.photos.value
                val photosLastIndex = photos.lastIndex
                val loadingState = viewModel.loadingState.value
                val alertState = viewModel.alertState.value

                val lazyListState: LazyListState = rememberLazyListState()

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (photosContainer, backgroundAlert, ribbonAlert) = createRefs()

                    if (photos.isNotEmpty()) {
                        LazyColumn(
                            state = lazyListState,
                            modifier = Modifier
                                .fillMaxSize()
                                .constrainAs(photosContainer) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        ) {
                            itemsIndexed(photos) { index, photo ->
                                PhotoCard(photo, ::onPhotoCardClick)

                                if (index == photosLastIndex && !loadingState) {
                                    onActive {
                                        loadMorePhotos()
                                    }
                                }
                            }
                        }
                    }

                    when(alertState) {
                        EmptySearch -> {
                            Surface(
                                modifier = Modifier.constrainAs(
                                    backgroundAlert
                                ) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }) {
                                BackgroundAlert(
                                    R.drawable.ic_search_off,
                                    R.string.no_photos_found
                                )
                            }
                        }
                        BadConnection -> {
                            Surface(modifier = Modifier.constrainAs(ribbonAlert) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                                AlertRibbon(getString(R.string.bad_unsplash_connection))
                            }
                        }
                        NoInternet -> {
                            Surface(modifier = Modifier.constrainAs(ribbonAlert) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                                AlertRibbon(getString(R.string.no_internet))
                            }
                        }
                        None -> Unit
                    }

                    if (loadingState) {
                        Surface(modifier = Modifier.constrainAs(ribbonAlert) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                            LoadingBar()
                        }
                    }
                }
            }
        }
    }

    private fun loadMorePhotos() {
        viewModel.loadMorePhotos()
    }

    private fun onPhotoCardClick(photo: PhotoVo) {
        viewModel.navigateToPhotoDetail(photo)
    }
}

