package eu.talich.waller.feature.collectiondetail.di

import eu.talich.waller.common.navigation.model.CollectionDetail
import eu.talich.waller.feature.collectiondetail.presentation.CollectionDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCollectionDetailModule = module {
    viewModel {
        (collection: CollectionDetail) -> CollectionDetailViewModel(
            get(), get(), get(), collection
        )
    }
}