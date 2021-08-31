package eu.talich.waller.library.unsplash.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.talich.waller.library.unsplash.data.CollectionRepositoryImpl
import eu.talich.waller.library.unsplash.data.PhotoRepositoryImpl
import eu.talich.waller.library.unsplash.data.api.UnsplashApi
import eu.talich.waller.library.unsplash.domain.*
import eu.talich.waller.library.unsplash.domain.repository.CollectionRepository
import eu.talich.waller.library.unsplash.domain.repository.PhotoRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val UNSPLASH_BASE_URL = "https://api.unsplash.com"

val libraryUnsplashModule = module {
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

    single<CollectionRepository> { CollectionRepositoryImpl(get()) }
    single<PhotoRepository> { PhotoRepositoryImpl(get()) }

    factory { GetCollectionPhotosUseCase(get()) }
    factory { GetFeaturedCollectionsUseCase(get()) }
    factory { GetPhotoDetailUseCase(get()) }
    factory { GetPhotosUseCase(get()) }
    factory { SearchCollectionsUseCase(get()) }
    factory { SearchPhotosUseCase(get()) }
}
