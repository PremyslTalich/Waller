package eu.talich.waller.library.unsplash.model

data class PhotoExifDto(
    val make: String?,
    val model: String?,
    val exposureTime: Float?,
    val aperture: Float?,
    val focalLength: Float?,
    val iso: Float?
)
