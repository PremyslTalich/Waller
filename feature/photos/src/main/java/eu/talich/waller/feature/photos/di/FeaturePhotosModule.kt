package eu.talich.waller.feature.photos.di

import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.feature.photos.presentation.PhotosViewModel
import eu.talich.waller.feature.photos.system.PhotosFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val featurePhotosModule = module {
    viewModel { PhotosViewModel(get(), get(), get(), get(), get()) }

    single { PhotosFragment() } bind MainScreenPage::class
}