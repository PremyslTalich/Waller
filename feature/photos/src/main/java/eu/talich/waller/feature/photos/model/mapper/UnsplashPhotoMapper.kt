package eu.talich.waller.feature.photos.model.mapper

import eu.talich.waller.feature.photos.model.BlurHashVo
import eu.talich.waller.feature.photos.model.PhotoVo
import eu.talich.waller.feature.photos.model.ThumbnailVo
import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.library.unsplash.model.PhotoDto
import kotlin.math.roundToInt

class UnsplashPhotoMapper: Mapper<PhotoDto, PhotoVo> {
    override fun map(from: PhotoDto): PhotoVo {
        val ratio = from.height.toDouble() / from.width

        val blurHashWidth = 20
        val blurHashHeight = (blurHashWidth * ratio).roundToInt()

        val thumbnailWidth = 400
        val thumbnailHeight = (thumbnailWidth * ratio).roundToInt()

        return with(from) {
            PhotoVo(
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
    }
}