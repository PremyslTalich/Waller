package eu.talich.waller.library.unsplash.domain

import eu.talich.waller.library.unsplash.domain.repository.CollectionRepository
import eu.talich.waller.library.unsplash.model.CollectionSearchResultDto

class SearchCollectionsUseCase(
    private val collectionRepository: CollectionRepository
) {
    private suspend fun doWork(query: String, page: Int): CollectionSearchResultDto {
        return collectionRepository.searchCollections(query, page)
    }

    suspend operator fun invoke(query: String, page: Int) = doWork(query, page)
}