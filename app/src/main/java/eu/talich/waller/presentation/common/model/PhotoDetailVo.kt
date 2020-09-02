package eu.talich.waller.presentation.common.model

import eu.talich.domain.model.PhotoExif
import eu.talich.domain.model.PhotoLocation
import eu.talich.domain.model.PhotoUrls
import eu.talich.domain.model.User
import java.io.Serializable
import java.util.*

data class PhotoDetailVo(
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
) : Serializable