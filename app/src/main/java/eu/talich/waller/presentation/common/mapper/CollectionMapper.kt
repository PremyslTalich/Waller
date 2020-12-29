package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.Collection
import eu.talich.domain.model.mapper.Mapper
import eu.talich.waller.presentation.common.model.CollectionVo
import eu.talich.waller.presentation.common.model.PhotoVo

class CollectionMapper(
    private val photoMapper: PhotoMapper
): Mapper<Collection, CollectionVo> {
    override fun map(from: Collection): CollectionVo {
        return with(from) {
            CollectionVo(
                id,
                title ?: "",
                description ?: "",
                totalPhotos,
                coverPhoto?.let { photoMapper.map(it) }
            )
        }
    }
}