package eu.talich.waller.system

import android.app.Application
import eu.talich.waller.di.appModule
import eu.talich.waller.feature.collectiondetail.di.featureCollectionDetailModule
import eu.talich.waller.feature.collections.di.featureCollectionsModule
import eu.talich.waller.feature.main.di.featureMainModule
import eu.talich.waller.feature.photodetail.di.featurePhotoDetailModule
import eu.talich.waller.feature.photos.di.featurePhotosModule
import eu.talich.waller.feature.search.di.featureSearchModule
import eu.talich.waller.library.internetobserver.di.libraryInternetObserverModule
import eu.talich.waller.library.navigation.di.libraryNavigationModule
import eu.talich.waller.library.search.di.librarySearchModule
import eu.talich.waller.library.unsplash.di.libraryOkHttpModule
import eu.talich.waller.library.unsplash.di.libraryUnsplashModule
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@FlowPreview
class WallerApplication : Application() {
    override fun onCreate(){
        super.onCreate()

        startKoin {
            androidContext(this@WallerApplication)
            modules(
                appModule,

                libraryInternetObserverModule,
                libraryNavigationModule,
                librarySearchModule,
                libraryUnsplashModule,
                libraryOkHttpModule,

                featureSearchModule,
                featurePhotosModule,
                featurePhotoDetailModule,
                featureMainModule,
                featureCollectionsModule,
                featureCollectionDetailModule
            )
        }
    }
}
