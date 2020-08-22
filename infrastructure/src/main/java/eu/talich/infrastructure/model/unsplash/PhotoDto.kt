package eu.talich.infrastructure.model.unsplash

data class PhotoDto (
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val color: String,
    val urls: PhotoUrlsDto?
)