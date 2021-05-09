package eu.talich.infrastructure.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.talich.data.source.CollectionsSource
import eu.talich.waller.library.internetobserver.device.InternetConnectionSource
import eu.talich.data.source.PhotoSource
import eu.talich.infrastructure.model.unsplash.mapper.*
import eu.talich.waller.library.unsplash.data.api.AuthorizationInterceptor
import eu.talich.waller.library.unsplash.data.api.UnsplashApi
import eu.talich.waller.library.unsplash.data.CollectionsSourceImpl
import eu.talich.waller.library.internetobserver.device.InternetConnectionSourceImpl
import eu.talich.waller.library.unsplash.data.PhotoSourceImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val UNSPLASH_BASE_URL = "https://api.unsplash.com"

val infrastructureModule = module {

    factory { eu.talich.waller.common.photo.model.PhotoMapper() }
    factory { eu.talich.waller.common.photo.model.PhotoSearchResultMapper(get()) }
    factory { eu.talich.waller.common.collection.model.CollectionMapper() }
    factory { eu.talich.waller.common.collection.model.CollectionSearchResultMapper(get()) }
    factory { eu.talich.waller.common.photo.model.PhotoDetailMapper(get()) }
    factory { eu.talich.waller.common.user.model.UserMapper() }
    factory<PhotoSource> {
        eu.talich.waller.library.unsplash.data.PhotoSourceImpl(
            get(),
            get(),
            get(),
            get()
        )
    }
    factory<CollectionsSource> {
        eu.talich.waller.library.unsplash.data.CollectionsSourceImpl(
            get(),
            get(),
            get(),
            get()
        )
    }
    factory<eu.talich.waller.library.internetobserver.device.InternetConnectionSource> {
        eu.talich.waller.library.internetobserver.device.InternetConnectionSourceImpl(
            androidContext()
        )
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(eu.talich.waller.library.unsplash.data.api.AuthorizationInterceptor())
            .addNetworkInterceptor(FlipperOkhttpInterceptor(get()))
            .build()
    }

    single<eu.talich.waller.library.unsplash.data.api.UnsplashApi> {
        Retrofit.Builder()
            .baseUrl(UNSPLASH_BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(eu.talich.waller.library.unsplash.data.api.UnsplashApi::class.java)
    }

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }
}