package eu.talich.waller.feature.photodetail.di

import eu.talich.waller.common.navigation.model.PhotoDetail
import eu.talich.waller.feature.photodetail.presentation.PhotoDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featurePhotoDetailModule = module {
    viewModel { (photo: PhotoDetail) -> PhotoDetailViewModel(get(), get(), get(), photo) }
}