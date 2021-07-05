package eu.talich.waller.library.unsplash.domain

import eu.talich.waller.library.unsplash.domain.repository.PhotoRepository
import eu.talich.waller.library.unsplash.model.PhotoSearchResultDto

class SearchPhotosUseCase(
    private val photoRepository: PhotoRepository
) {
    private suspend fun doWork(query: String, page: Int): PhotoSearchResultDto {
        return photoRepository.searchPhotos(query, page)
    }

    suspend operator fun invoke(query: String, page: Int) = doWork(query, page)
}
