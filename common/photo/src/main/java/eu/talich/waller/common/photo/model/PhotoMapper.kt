package eu.talich.waller.common.photo.model

import eu.talich.domain.model.Photo
import eu.talich.waller.library.unsplash.model.PhotoDto
import eu.talich.waller.library.mapper.model.Mapper

class PhotoMapper:
    eu.talich.waller.library.mapper.model.Mapper<eu.talich.waller.library.unsplash.model.PhotoDto, Photo> {
    override fun map(from: eu.talich.waller.library.unsplash.model.PhotoDto): Photo {
        return with(from) {
            Photo(
                id,
                description,
                width,
                height,
                color,
                PhotoUrls(
                    urls?.raw,
                    urls?.full,
                    urls?.regular,
                    urls?.small,
                    urls?.thumb
                ),
                blurHash
            )
        }
    }
}