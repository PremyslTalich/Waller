package eu.talich.waller.feature.collections.model

import java.io.Serializable

data class CollectionVo (
    val id: String,
    val title: String,
    val description: String,
    val totalPhotos: Int,
    val thumbnail: ThumbnailVo?
) : Serializable