package eu.talich.waller

import android.app.Application
import eu.talich.data.di.dataModule
import eu.talich.domain.di.domainModule
import eu.talich.infrastructure.di.infrastructureModule
import eu.talich.waller.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WallerApplication : Application() {
    override fun onCreate(){
        super.onCreate()

        startKoin {
            androidContext(this@WallerApplication)
            modules(
                presentationModule,
                dataModule,
                domainModule,
                infrastructureModule
            )
        }
    }
}