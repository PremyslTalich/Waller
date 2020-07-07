package eu.talich.waller.di

import eu.talich.waller.presentation.main.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MainViewModel(get())
    }
}