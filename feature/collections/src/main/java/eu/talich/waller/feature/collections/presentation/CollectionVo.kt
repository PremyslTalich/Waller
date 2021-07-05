package eu.talich.waller.feature.collections.presentation

import java.io.Serializable

data class CollectionVo (
    val id: String,
    val title: String,
    val description: String,
    val totalPhotos: Int,
    val coverPhoto: CoverPhotoVo
) : Serializable