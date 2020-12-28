package eu.talich.infrastructure.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.talich.data.source.CollectionsSource
import eu.talich.data.source.PhotoSource
import eu.talich.infrastructure.model.unsplash.mapper.*
import eu.talich.infrastructure.network.unsplash.AuthorizationInterceptor
import eu.talich.infrastructure.network.unsplash.UnsplashApi
import eu.talich.infrastructure.source.CollectionsSourceImpl
import eu.talich.infrastructure.source.PhotoSourceImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val UNSPLASH_BASE_URL = "https://api.unsplash.com"

val infrastructureModule = module {

    factory { PhotoMapper() }
    factory { PhotoSearchResultMapper(get()) }
    factory { CollectionMapper() }
    factory { CollectionSearchResultMapper(get()) }
    factory { PhotoDetailMapper(get()) }
    factory { UserMapper() }
    factory<PhotoSource> { PhotoSourceImpl(get(), get(), get(), get()) }
    factory<CollectionsSource> { CollectionsSourceImpl(get(), get(), get(), get()) }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .addNetworkInterceptor(FlipperOkhttpInterceptor(get()))
            .build()
    }

    single<UnsplashApi> {
        Retrofit.Builder()
            .baseUrl(UNSPLASH_BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(UnsplashApi::class.java)
    }

    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }
}