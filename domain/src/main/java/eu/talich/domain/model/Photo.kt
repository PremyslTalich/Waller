package eu.talich.domain.model

data class Photo(
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val color: String,
    val urls: PhotoUrls?
)