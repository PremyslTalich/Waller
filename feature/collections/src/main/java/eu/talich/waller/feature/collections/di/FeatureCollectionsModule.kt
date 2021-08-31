package eu.talich.waller.feature.collections.di

import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.feature.collections.presentation.CollectionsViewModel
import eu.talich.waller.feature.collections.system.CollectionsFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val featureCollectionsModule = module {
    viewModel {
        CollectionsViewModel(
            get(), get(), get(), get(), get()
        )
    }

    single { CollectionsFragment() } bind MainScreenPage::class
}