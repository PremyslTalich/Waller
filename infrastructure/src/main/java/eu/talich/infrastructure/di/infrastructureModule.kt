package eu.talich.infrastructure.di

import eu.talich.data.source.ImageSource
import eu.talich.infrastructure.network.unsplash.AuthorizationInterceptor
import eu.talich.infrastructure.network.unsplash.UnsplashApi
import eu.talich.infrastructure.source.ImageSourceImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val UNSPLASH_BASE_URL = "https://api.unsplash.com"

val infrastructureModule = module {

    factory<ImageSource> { ImageSourceImpl(get()) }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    single<UnsplashApi> {
        Retrofit.Builder()
            .baseUrl(UNSPLASH_BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

}