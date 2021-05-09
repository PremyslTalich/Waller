package eu.talich.waller.library.internetobserver.di

import eu.talich.waller.library.internetobserver.device.InternetConnectionControllerImpl
import eu.talich.waller.library.internetobserver.device.InternetConnectivityManager
import eu.talich.waller.library.internetobserver.domain.InternetConnectionController
import eu.talich.waller.library.internetobserver.domain.ObserveInternetConnectionUseCase
import eu.talich.waller.library.internetobserver.system.InternetConnectivityManagerImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val internetObserverModule = module {
    single<InternetConnectivityManager> { InternetConnectivityManagerImpl(androidContext()) }
    factory<InternetConnectionController> { InternetConnectionControllerImpl(get()) }
    factory { ObserveInternetConnectionUseCase(get()) }
}