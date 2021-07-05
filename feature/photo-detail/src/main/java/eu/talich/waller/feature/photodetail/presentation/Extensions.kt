package eu.talich.waller.feature.photodetail.presentation

import eu.talich.waller.library.unsplash.model.PhotoDetailDto

fun PhotoDetailDto.toPhotoDetailVo(): PhotoDetailVo {
    return PhotoDetailVo(
        PhotoDetailVo.UserVo(
            user.id,
            user.username,
            user.name
        ),
        description,
        createdAt,
        likes,
        PhotoDetailVo.LocationVo(
            location.city,
            location.country,
            location.position?.latitude,
            location.position?.longitude
        ),
        tags.map { it.title }
    )
}