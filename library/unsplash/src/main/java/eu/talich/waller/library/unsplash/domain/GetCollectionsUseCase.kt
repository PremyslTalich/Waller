package eu.talich.waller.library.unsplash.domain

import eu.talich.waller.library.unsplash.domain.repository.CollectionRepository
import eu.talich.waller.library.unsplash.model.CollectionDto

class GetFeaturedCollectionsUseCase(
    private val collectionRepository: CollectionRepository
) {
    private suspend fun doWork(page: Int): List<CollectionDto> {
        return collectionRepository.getFeaturedCollections(page)
    }

    suspend operator fun invoke(page: Int) = doWork(page)
}