package eu.talich.waller.feature.photos.presentation

import eu.talich.waller.library.unsplash.model.PhotoDto
import kotlin.math.roundToInt

internal fun PhotoDto.toPhotoVo(): PhotoVo {
    val ratio = height.toDouble() / width

    val blurHashWidth = 20
    val blurHashHeight = (blurHashWidth * ratio).roundToInt()

    val thumbnailWidth = 400
    val thumbnailHeight = (thumbnailWidth * ratio).roundToInt()

    return PhotoVo(
        id,
        description,
        urls.full,
        color,
        blurHash?.let {
            BlurHashVo(
                it,
                blurHashWidth,
                blurHashHeight
            )
        },
        ThumbnailVo(
            "${urls.thumb}?w=$thumbnailWidth&h=$thumbnailHeight&fit=crop&crop=entropy",
            urls.thumb,
            thumbnailWidth,
            thumbnailHeight
        )
    )
}
