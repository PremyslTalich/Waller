package eu.talich.waller.library.unsplash.domain

import eu.talich.waller.library.unsplash.domain.repository.CollectionRepository
import eu.talich.waller.library.unsplash.model.PhotoDto

class GetCollectionPhotosUseCase(
    private val collectionRepository: CollectionRepository
) {
    private suspend fun doWork(collectionId: String, page: Int): List<PhotoDto> {
        return collectionRepository.getCollectionPhotos(collectionId, page)
    }

    suspend operator fun invoke(collectionId: String, page: Int) = doWork(collectionId, page)
}