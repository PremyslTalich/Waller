package eu.talich.waller.common.collection.model.mapper

import eu.talich.waller.common.collection.model.CollectionSearchResult
import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.library.unsplash.model.CollectionSearchResultDto

class CollectionSearchResultMapper(
    private val collectionMapper: CollectionMapper
): Mapper<CollectionSearchResultDto, CollectionSearchResult> {
    override fun map(from: CollectionSearchResultDto): CollectionSearchResult {
        return with(from) {
            CollectionSearchResult(
                total,
                totalPages,
                results.map {
                    collectionMapper.map(it)
                }
            )
        }
    }
}