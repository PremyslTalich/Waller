package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.Photo
import eu.talich.domain.model.mapper.Mapper
import eu.talich.waller.presentation.common.model.BlurHashVo
import eu.talich.waller.presentation.common.model.PhotoVo
import eu.talich.waller.presentation.common.model.ThumbnailVo
import kotlin.math.roundToInt

class PhotoMapper: Mapper<Photo, PhotoVo> {
    override fun map(from: Photo): PhotoVo {
        val ratio = from.height.toDouble() / from.width

        val blurHashWidth = 20
        val blurHashHeight = (blurHashWidth * ratio).roundToInt()

        val thumbnailWidth = 400
        val thumbnailHeight = (thumbnailWidth * ratio).roundToInt()

        return with(from) {
            PhotoVo(
                id,
                description,
                width,
                height,
                color,
                urls.regular,
                urls.full,
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
    }
}