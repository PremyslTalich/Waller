package eu.talich.infrastructure.model.unsplash

import com.squareup.moshi.Json

data class CollectionDto (
    val id: String,
    val title: String?,
    val description: String?,
    @Json(name="total_photos")
    val totalPhotos: Int,
    @Json(name="cover_photo")
    val coverPhoto: PhotoDto?
)