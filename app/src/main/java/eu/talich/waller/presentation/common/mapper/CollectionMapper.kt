package eu.talich.waller.presentation.common.mapper

import eu.talich.domain.model.Collection
import eu.talich.domain.model.mapper.Mapper
import eu.talich.waller.presentation.common.model.CollectionVo

class CollectionMapper: Mapper<Collection, CollectionVo> {
    override fun map(from: Collection): CollectionVo {
        return CollectionVo(
            from.id,
            from.title ?: "",
            from.description ?: "",
            from.coverPhoto?.urls?.small
        )
    }
}