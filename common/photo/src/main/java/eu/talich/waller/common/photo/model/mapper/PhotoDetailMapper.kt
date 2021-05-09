package eu.talich.waller.common.photo.model.mapper

import eu.talich.waller.common.photo.model.PhotoDetail
import eu.talich.waller.common.photo.model.PhotoExif
import eu.talich.waller.common.photo.model.PhotoLocation
import eu.talich.waller.common.photo.model.PhotoUrls
import eu.talich.waller.common.user.model.mapper.UserMapper
import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.library.unsplash.model.PhotoDetailDto

class PhotoDetailMapper(
    private val userMapper: UserMapper
): Mapper<PhotoDetailDto, PhotoDetail> {
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
                    urls.raw,
                    urls.full,
                    urls.regular,
                    urls.small,
                    urls.thumb
                ),
            )
        }
    }
}