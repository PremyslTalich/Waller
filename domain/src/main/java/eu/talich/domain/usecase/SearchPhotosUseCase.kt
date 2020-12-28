package eu.talich.domain.usecase

import eu.talich.domain.model.PhotoSearchResult
import eu.talich.domain.repository.PhotoRepository

class SearchPhotosUseCase(
    private val photoRepository: PhotoRepository
) {
    private suspend fun doWork(query: String, page: Int): PhotoSearchResult {
        return photoRepository.searchPhotos(query, page)
    }

    suspend operator fun invoke(query: String, page: Int) = doWork(query, page)
}
