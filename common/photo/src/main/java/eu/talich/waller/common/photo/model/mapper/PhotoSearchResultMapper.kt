package eu.talich.waller.common.photo.model.mapper

import eu.talich.waller.common.photo.model.PhotoMapper
import eu.talich.waller.common.photo.model.PhotoSearchResult
import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.library.unsplash.model.PhotoSearchResultDto

class PhotoSearchResultMapper(
    private val photoMapper: PhotoMapper
): Mapper<PhotoSearchResultDto, PhotoSearchResult> {
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