package eu.talich.infrastructure.network.unsplash

import eu.talich.infrastructure.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder().apply {
            header("Authorization", "Client-ID ${BuildConfig.WALLER_ACCESS_KEY}")
        }

        return chain.proceed(requestBuilder.build())
    }
}
