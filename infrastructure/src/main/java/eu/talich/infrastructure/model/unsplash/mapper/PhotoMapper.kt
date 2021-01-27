package eu.talich.infrastructure.model.unsplash.mapper

import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoUrls
import eu.talich.infrastructure.model.unsplash.PhotoDto
import eu.talich.domain.model.mapper.Mapper

class PhotoMapper:
    Mapper<PhotoDto, Photo> {
    override fun map(from: PhotoDto): Photo {
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