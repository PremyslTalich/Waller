package eu.talich.domain.model

data class Image(
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val urls: ImageUrls?
)