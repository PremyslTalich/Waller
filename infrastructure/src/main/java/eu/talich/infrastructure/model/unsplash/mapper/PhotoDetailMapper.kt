package eu.talich.infrastructure.model.unsplash.mapper

import eu.talich.domain.model.*
import eu.talich.domain.model.mapper.Mapper
import eu.talich.infrastructure.model.unsplash.PhotoDetailDto

class PhotoDetailMapper(
    private val userMapper: UserMapper
):
    Mapper<PhotoDetailDto, PhotoDetail> {
    override fun map(from: PhotoDetailDto): PhotoDetail {
        return with(from) {
            PhotoDetail(
                id,
                description,
                createdAt,
                updatedAt,
                width,
                height,
                color,
                downloads,
                likes,
                PhotoExif(
                    exif.make,
                    exif.model,
                    exif.exposureTime,
                    exif.aperture,
                    exif.focalLength,
                    exif.iso
                ),
                PhotoLocation(
                    location.city,
                    location.country,
                    location.position?.latitude,
                    location.position?.longitude,
                ),
                tags.map {
                    it.title
                },
                userMapper.map(user),
                PhotoUrls(
                    urls?.raw,
                    urls?.full,
                    urls?.regular,
                    urls?.small,
                    urls?.thumb
                ),
            )
        }
    }
}