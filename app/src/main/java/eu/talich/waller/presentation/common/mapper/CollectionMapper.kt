package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.Collection
import eu.talich.domain.model.mapper.Mapper
import eu.talich.waller.presentation.common.model.CollectionVo
import eu.talich.waller.presentation.common.model.PhotoVo

class CollectionMapper(
    private val photoMapper: PhotoMapper
): Mapper<Collection, CollectionVo> {
    override fun map(from: Collection): CollectionVo {
        return CollectionVo(
            from.id,
            from.title ?: "",
            from.description ?: "",
            from.coverPhoto?.let { photoMapper.map(it) }
        )
    }
}