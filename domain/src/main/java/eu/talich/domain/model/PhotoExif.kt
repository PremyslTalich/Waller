package eu.talich.domain.model

data class PhotoExif(
    val make: String?,
    val model: String?,
    val exposureTime: Float?,
    val aperture: Float?,
    val focalLength: Float?,
    val iso: Float?
)
