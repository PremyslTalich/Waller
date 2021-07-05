package eu.talich.waller.feature.collectiondetail.presentation

import eu.talich.waller.common.navigation.model.CollectionDetail
import eu.talich.waller.common.navigation.model.WallerRouterDestinations
import eu.talich.waller.library.unsplash.model.PhotoDto

internal fun PhotoDto.toPhotoVo(): PhotoVo {
    return PhotoVo(
        id,
        description,
        urls.full,
        urls.thumb,
        color
    )
}

internal fun CollectionDetail.CoverPhoto.toPhotoVo(): PhotoVo {
    return PhotoVo(
        id,
        null,
        url,
        url,
        color
    )
}