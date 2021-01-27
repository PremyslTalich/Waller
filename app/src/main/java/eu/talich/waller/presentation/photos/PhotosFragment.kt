package eu.talich.waller.presentation.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.onActive
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import eu.talich.waller.R
import eu.talich.waller.presentation.common.TabbedFragment
import eu.talich.waller.presentation.common.ui.AlertRibbon
import eu.talich.waller.presentation.common.ui.BackgroundAlert
import eu.talich.waller.presentation.common.ui.LoadingBar
import eu.talich.waller.presentation.main.MainFragmentDirections
import eu.talich.waller.presentation.photos.ui.PhotoCard
import eu.talich.waller.presentation.photos.vm.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment(), TabbedFragment {

    override val title: Int
        get() = R.string.photos_title

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

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (photosContainer, backgroundAlert, ribbonAlert) = createRefs()

                    if (photos.isNotEmpty()) {
                        LazyColumn(
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
                                PhotoCard(photo) {
                                    findNavController().navigate(
                                        MainFragmentDirections
                                            .actionMainFragmentToPhotoDetailFragment(it)
                                    )
                                }

                                if (index == photosLastIndex && !loadingState) {
                                    onActive {
                                        viewModel.loadMorePhotos()
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
}
