package eu.talich.waller.feature.collections.model.mapper

import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.library.unsplash.model.CollectionDto
import eu.talich.waller.feature.collections.model.CollectionVo

class UnsplashCollectionMapper: Mapper<CollectionDto, CollectionVo> {
    override fun map(from: CollectionDto): CollectionVo {
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