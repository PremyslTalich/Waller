package eu.talich.waller.di

import eu.talich.waller.device.WallerRouter
import eu.talich.waller.library.navigation.model.Router
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { WallerRouter() } bind Router::class
}
