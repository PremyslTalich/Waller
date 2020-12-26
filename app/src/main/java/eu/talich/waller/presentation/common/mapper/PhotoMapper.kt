package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.Photo
import eu.talich.domain.model.mapper.Mapper
import eu.talich.waller.presentation.common.model.PhotoVo

class PhotoMapper: Mapper<Photo, PhotoVo> {
    override fun map(from: Photo): PhotoVo {
        return with(from) {
            PhotoVo(
                id,
                description,
                width,
                height,
                color,
                urls?.small,
                urls?.regular,
                urls?.full
            )
        }
    }
}