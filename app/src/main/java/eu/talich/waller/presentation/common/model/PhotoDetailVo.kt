package eu.talich.waller.presentation.common.model

import eu.talich.waller.common.photo.model.PhotoExif
import eu.talich.waller.common.photo.model.PhotoLocation
import eu.talich.waller.common.photo.model.PhotoUrls
import eu.talich.waller.common.user.model.User
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
    val exif: eu.talich.waller.common.photo.model.PhotoExif,
    val location: eu.talich.waller.common.photo.model.PhotoLocation,
    val tags: List<String>,
    val user: eu.talich.waller.common.user.model.User,
    val urls: eu.talich.waller.common.photo.model.PhotoUrls?
) : Serializable