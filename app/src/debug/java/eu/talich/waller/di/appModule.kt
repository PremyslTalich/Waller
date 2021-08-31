package eu.talich.waller.di

import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import eu.talich.waller.device.WallerRouter
import eu.talich.waller.library.navigation.model.Router
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { NetworkFlipperPlugin() }

    single { WallerRouter() } bind Router::class
}