package eu.talich.domain.usecase

import eu.talich.domain.model.Photo
import eu.talich.domain.repository.PhotoRepository

class GetPhotosUseCase(
    private val photoRepository: PhotoRepository
) {
    private suspend fun doWork(page: Int): List<Photo> {
        return photoRepository.getPhotos(page)
    }

    suspend operator fun invoke(page: Int) = doWork(page)
}