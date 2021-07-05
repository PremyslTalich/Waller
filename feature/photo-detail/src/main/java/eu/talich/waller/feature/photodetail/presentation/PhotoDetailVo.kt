package eu.talich.waller.feature.photodetail.presentation

import java.util.*

data class PhotoDetailVo(
    val user: UserVo,
    val description: String?,
    val createdAt: Date,
    val likes: Int,
    val location: LocationVo,
    val tags: List<String>
) {
    data class UserVo(
        val id: String,
        val username: String,
        val name: String
    )

    data class LocationVo(
        val city: String?,
        val country: String?,
        val latitude: Float?,
        val longitude: Float?,
    )
}
