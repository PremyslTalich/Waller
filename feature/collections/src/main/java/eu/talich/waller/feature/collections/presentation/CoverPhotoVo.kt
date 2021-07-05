package eu.talich.waller.feature.collections.presentation

data class CoverPhotoVo(
    val id: String,
    val rawUrl: String,
    val thumbnailUrl: String,
    val width: Int,
    val height: Int,
    val color: String
)