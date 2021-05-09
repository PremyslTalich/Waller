package eu.talich.waller.library.unsplash.model

data class PhotoLocationDto(
    val city: String?,
    val country: String?,
    val position: PositionDto?
) {
    data class PositionDto(
        val latitude: Float?,
        val longitude: Float?
    )
}
