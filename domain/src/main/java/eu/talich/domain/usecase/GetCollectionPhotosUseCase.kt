package eu.talich.domain.usecase

import eu.talich.domain.model.Photo
import eu.talich.domain.repository.CollectionRepository


class GetCollectionPhotosUseCase(
    private val collectionRepository: CollectionRepository
) {
    private suspend fun doWork(collectionId: String, page: Int): List<Photo> {
        return collectionRepository.getCollectionPhotos(collectionId, page)
    }

    suspend operator fun invoke(collectionId: String, page: Int) = doWork(collectionId, page)
}