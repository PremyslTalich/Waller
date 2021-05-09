package eu.talich.waller.common.collection.model.mapper

import eu.talich.waller.common.collection.model.Collection
import eu.talich.waller.common.photo.model.PhotoMapper
import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.library.unsplash.model.CollectionDto

class CollectionMapper(
    private val photoMapper: PhotoMapper
): Mapper<CollectionDto, Collection> {
    override fun map(from: CollectionDto): Collection {
        return with(from) {
            Collection(
                id,
                title,
                description,
                totalPhotos,
                coverPhoto?.let {
                    photoMapper.map(it)
                }
            )
        }
    }
}