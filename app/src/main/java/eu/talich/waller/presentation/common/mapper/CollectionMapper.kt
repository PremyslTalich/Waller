package eu.talich.waller.presentation.common.mapper

import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.presentation.common.model.CollectionVo

class CollectionMapper(
    private val photoMapper: PhotoMapper
): eu.talich.waller.library.mapper.model.Mapper<Collection, CollectionVo> {
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