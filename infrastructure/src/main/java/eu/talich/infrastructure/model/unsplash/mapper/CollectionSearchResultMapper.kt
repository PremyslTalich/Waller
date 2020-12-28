package eu.talich.infrastructure.model.unsplash.mapper

import eu.talich.domain.model.CollectionSearchResult
import eu.talich.domain.model.mapper.Mapper
import eu.talich.infrastructure.model.unsplash.CollectionSearchResultDto

class CollectionSearchResultMapper(
    private val collectionMapper: CollectionMapper
):
    Mapper<CollectionSearchResultDto, CollectionSearchResult> {
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