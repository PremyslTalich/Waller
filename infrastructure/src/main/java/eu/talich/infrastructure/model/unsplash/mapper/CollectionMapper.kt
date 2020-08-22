package eu.talich.infrastructure.model.unsplash.mapper

import eu.talich.domain.model.Collection
import eu.talich.domain.model.mapper.Mapper
import eu.talich.infrastructure.model.unsplash.CollectionDto

class CollectionMapper: Mapper<CollectionDto, Collection> {
    override fun map(from: CollectionDto): Collection {
        return Collection(
            from.id,
            from.title,
            from.description,
            from.totalPhotos,
            from.coverPhoto?.let {
                PhotoMapper().map(it)
            }
        )
    }
}