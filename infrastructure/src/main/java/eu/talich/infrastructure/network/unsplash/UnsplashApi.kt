package eu.talich.infrastructure.network.unsplash

import eu.talich.infrastructure.model.ImageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/photos")
    suspend fun getImages(
        @Query("page") page: Int
    ): List<ImageDto>
}