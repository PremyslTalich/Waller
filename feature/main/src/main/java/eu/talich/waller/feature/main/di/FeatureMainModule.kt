package eu.talich.waller.feature.main.di

import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.feature.main.presentation.MainFragmentViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureMainModule = module {
    viewModel {
        MainFragmentViewModel(
            androidApplication(),
            getAll<MainScreenPage>(),
            get()
        )
    }
}