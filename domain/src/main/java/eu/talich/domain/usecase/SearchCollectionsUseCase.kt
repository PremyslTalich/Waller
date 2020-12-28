package eu.talich.domain.usecase

import eu.talich.domain.model.CollectionSearchResult
import eu.talich.domain.repository.CollectionRepository

class SearchCollectionsUseCase(
    private val collectionRepository: CollectionRepository
) {
    private suspend fun doWork(query: String, page: Int): CollectionSearchResult {
        return collectionRepository.searchCollections(query, page)
    }

    suspend operator fun invoke(query: String, page: Int) = doWork(query, page)
}