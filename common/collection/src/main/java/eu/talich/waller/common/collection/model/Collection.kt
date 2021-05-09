package eu.talich.waller.common.collection.model

import eu.talich.waller.common.photo.model.Photo

data class Collection(
    val id: String,
    val title: String?,
    val description: String?,
    val totalPhotos: Int,
    val coverPhoto: Photo?
)