package eu.talich.domain.model

import java.util.*

data class PhotoDetail(
    val id: String,
    val description: String?,
    val createdAt: Date,
    val updatedAt: Date,
    val width: Int,
    val height: Int,
    val color: String,
    val downloads: Int,
    val likes: Int,
    val exif: PhotoExif,
    val location: PhotoLocation,
    val tags: List<String>,
    val user: User,
    val urls: PhotoUrls?
)