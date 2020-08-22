package eu.talich.infrastructure.network.unsplash

import eu.talich.infrastructure.model.unsplash.CollectionDto
import eu.talich.infrastructure.model.unsplash.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int
    ): List<PhotoDto>

    @GET("/collections/featured")
    suspend fun getFeaturedCollections(
        @Query("page") page: Int
    ): List<CollectionDto>

    @GET("/collections/{id}/photos")
    suspend fun getCollectionPhotos(
        @Path("id") collectionId: String,
        @Query("page") page: Int
    ): List<PhotoDto>
}