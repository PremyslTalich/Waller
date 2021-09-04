package eu.talich.waller.library.unsplash.di

import eu.talich.waller.library.unsplash.data.api.AuthorizationInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module

val libraryOkHttpModule = module {
    factory {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }
}