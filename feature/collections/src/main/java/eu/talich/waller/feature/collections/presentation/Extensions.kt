package eu.talich.waller.feature.collections.presentation

import eu.talich.waller.library.unsplash.model.CollectionDto

fun CollectionDto.toCollectionVo(): CollectionVo {
    return CollectionVo(
        id,
        title ?: "",
        description ?: "",
        totalPhotos,
        CoverPhotoVo(
            id = coverPhoto.id,
            thumbnailUrl = coverPhoto.urls.regular,
            rawUrl = coverPhoto.urls.raw,
            width = coverPhoto.width,
            height = coverPhoto.height,
            color = coverPhoto.color
        )
    )
}