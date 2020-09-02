package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.PhotoDetail
import eu.talich.domain.model.mapper.Mapper
import eu.talich.waller.presentation.common.model.PhotoDetailVo

class PhotoDetailMapper: Mapper<PhotoDetail, PhotoDetailVo> {
    override fun map(from: PhotoDetail): PhotoDetailVo {
        return with (from) {
            PhotoDetailVo(
                id,
                description,
                createdAt,
                updatedAt,
                width,
                height,
                color,
                downloads,
                likes,
                exif,
                location,
                tags,
                user,
                urls
            )
        }
    }
}