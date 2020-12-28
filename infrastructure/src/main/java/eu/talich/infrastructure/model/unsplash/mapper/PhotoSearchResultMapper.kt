package eu.talich.infrastructure.model.unsplash.mapper

import eu.talich.domain.model.PhotoSearchResult
import eu.talich.domain.model.mapper.Mapper
import eu.talich.infrastructure.model.unsplash.PhotoSearchResultDto

class PhotoSearchResultMapper(
    private val photoMapper: PhotoMapper
):
    Mapper<PhotoSearchResultDto, PhotoSearchResult> {
    override fun map(from: PhotoSearchResultDto): PhotoSearchResult {
        return with(from) {
            PhotoSearchResult(
                total,
                totalPages,
                results.map {
                    photoMapper.map(it)
                }
            )
        }
    }
}