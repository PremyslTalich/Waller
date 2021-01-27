package eu.talich.infrastructure.model.unsplash

import com.squareup.moshi.Json

data class PhotoDto (
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val color: String,
    val urls: PhotoUrlsDto,
    @Json(name = "blur_hash") val blurHash: String?
)