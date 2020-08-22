package eu.talich.waller

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import eu.talich.data.di.dataModule
import eu.talich.domain.di.domainModule
import eu.talich.infrastructure.di.infrastructureModule
import eu.talich.waller.di.presentationModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WallerApplication : Application() {
    private val networkFlipperPlugin: NetworkFlipperPlugin by inject()

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

        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client: FlipperClient = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.addPlugin(networkFlipperPlugin)
            client.start()
        }
    }
}