package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.Photo
import eu.talich.domain.model.mapper.Mapper
import eu.talich.waller.presentation.common.model.PhotoVo

class PhotoMapper: Mapper<Photo, PhotoVo> {
    override fun map(from: Photo): PhotoVo {
        return PhotoVo(
            from.id,
            from.description,
            from.width,
            from.height,
            from.color,
            from.urls?.small,
            from.urls?.regular
        )
    }
}