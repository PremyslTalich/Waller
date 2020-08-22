package eu.talich.infrastructure.model.unsplash.mapper

import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoUrls
import eu.talich.infrastructure.model.unsplash.PhotoDto
import eu.talich.domain.model.mapper.Mapper

class PhotoMapper:
    Mapper<PhotoDto, Photo> {
    override fun map(from: PhotoDto): Photo {
        return Photo(
            from.id,
            from.description,
            from.width,
            from.height,
            from.color,
            PhotoUrls(
                from.urls?.raw,
                from.urls?.full,
                from.urls?.regular,
                from.urls?.small,
                from.urls?.thumb
            )
        )
    }
}