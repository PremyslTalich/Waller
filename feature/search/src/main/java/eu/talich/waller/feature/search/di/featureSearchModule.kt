package eu.talich.waller.feature.search.di

import eu.talich.waller.feature.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureSearchModule = module {
    viewModel { SearchViewModel(get(), get()) }
}