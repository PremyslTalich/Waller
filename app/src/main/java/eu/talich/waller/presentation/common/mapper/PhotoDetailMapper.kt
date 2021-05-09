package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.PhotoDetail
import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.presentation.common.model.PhotoDetailVo

class PhotoDetailMapper: eu.talich.waller.library.mapper.model.Mapper<PhotoDetail, PhotoDetailVo> {
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