package eu.talich.infrastructure.model.unsplash

data class PhotoUrlsDto(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)