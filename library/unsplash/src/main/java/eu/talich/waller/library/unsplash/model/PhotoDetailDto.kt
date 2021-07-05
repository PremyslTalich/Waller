package eu.talich.waller.library.unsplash.model

import com.squareup.moshi.Json
import java.util.*

data class PhotoDetailDto (
    val id: String,
    val description: String?,
    @Json(name = "created_at") val createdAt: Date,
    @Json(name = "updated_at") val updatedAt: Date,
    val width: Int,
    val height: Int,
    val color: String,
    val downloads: Int,
    val likes: Int,
    val exif: PhotoExifDto,
    val location: PhotoLocationDto,
    val tags: List<TagDto>,
    val user: UserDto,
    val urls: PhotoUrlsDto
)