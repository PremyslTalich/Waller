package eu.talich.waller.library.unsplash.domain

import eu.talich.waller.library.unsplash.domain.repository.PhotoRepository
import eu.talich.waller.library.unsplash.model.PhotoDto

class GetPhotosUseCase(
    private val photoRepository: PhotoRepository
) {
    private suspend fun doWork(page: Int): List<PhotoDto> {
        return photoRepository.getPhotos(page)
    }

    suspend operator fun invoke(page: Int) = doWork(page)
}