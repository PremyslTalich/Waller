package eu.talich.domain.usecase

import eu.talich.domain.model.Collection
import eu.talich.domain.repository.CollectionRepository

class GetFeaturedCollectionsUseCase(
    private val collectionRepository: CollectionRepository
) {
    private suspend fun doWork(page: Int): List<Collection> {
        return collectionRepository.getFeaturedCollections(page)
    }

    suspend operator fun invoke(page: Int) = doWork(page)
}