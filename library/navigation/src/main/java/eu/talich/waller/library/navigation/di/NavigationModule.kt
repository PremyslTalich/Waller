package eu.talich.waller.library.navigation.di

import eu.talich.waller.library.navigation.device.RouterControllerImpl
import eu.talich.waller.library.navigation.domain.NavigateUseCase
import eu.talich.waller.library.navigation.domain.controller.RouterController
import org.koin.dsl.module

val libraryNavigationModule = module {
    single<RouterController> { RouterControllerImpl(get()) }

    factory { NavigateUseCase(get()) }
}