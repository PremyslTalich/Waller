package eu.talich.infrastructure.network.unsplash

import eu.talich.infrastructure.model.unsplash.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int
    ): List<PhotoDto>

    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query ("query") query: String,
        @Query("page") page: Int
    ): PhotoSearchResultDto

    @GET("/photos/{id}")
    suspend fun getPhotoDetail(
        @Path("id") photoId: String
    ): PhotoDetailDto

    @GET("/collections/featured")
    suspend fun getFeaturedCollections(
        @Query("page") page: Int
    ): List<CollectionDto>


    @GET("/search/collections")
    suspend fun searchCollections(
        @Query ("query") query: String,
        @Query("page") page: Int
    ): CollectionSearchResultDto

    @GET("/collections/{id}/photos")
    suspend fun getCollectionPhotos(
        @Path("id") collectionId: String,
        @Query("page") page: Int
    ): List<PhotoDto>
}