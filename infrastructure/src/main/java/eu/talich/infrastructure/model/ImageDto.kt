package eu.talich.infrastructure.model

data class ImageDto (
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val urls: ImageUrlsDto?
)