package eu.talich.waller.di

import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import eu.talich.waller.presentation.collectiondetail.vm.CollectionDetailViewModel
import eu.talich.waller.presentation.collections.CollectionsFragment
import eu.talich.waller.presentation.common.model.CollectionVo
import eu.talich.waller.presentation.common.mapper.CollectionMapper
import eu.talich.waller.presentation.collections.vm.CollectionsViewModel
import eu.talich.waller.presentation.main.vm.MainFragmentViewModel
import eu.talich.waller.presentation.main.vm.MainViewModel
import eu.talich.waller.presentation.photos.PhotosFragment
import eu.talich.waller.presentation.common.mapper.PhotoMapper
import eu.talich.waller.presentation.common.model.PhotoVo
import eu.talich.waller.presentation.photodetail.vm.PhotoDetailViewModel
import eu.talich.waller.presentation.photos.vm.PhotosViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single { NetworkFlipperPlugin() }

    viewModel { MainViewModel() }
    viewModel { MainFragmentViewModel(androidApplication(), listOf(
        PhotosFragment(),
        CollectionsFragment()
    )) }

    viewModel { (photo: PhotoVo) -> PhotoDetailViewModel(photo) }

    viewModel { PhotosViewModel(get(), get()) }
    factory { PhotoMapper() }

    viewModel { CollectionsViewModel(get(), get()) }
    factory { CollectionMapper() }

    viewModel { (collection: CollectionVo) -> CollectionDetailViewModel(get(), collection, get()) }
}