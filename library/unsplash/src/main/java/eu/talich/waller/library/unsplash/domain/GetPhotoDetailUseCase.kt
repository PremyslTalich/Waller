package eu.talich.waller.library.unsplash.domain

import eu.talich.waller.library.unsplash.domain.repository.PhotoRepository
import eu.talich.waller.library.unsplash.model.PhotoDetailDto

class GetPhotoDetailUseCase(
    private val photoRepository: PhotoRepository
) {
    private suspend fun doWork(photoId: String): PhotoDetailDto {
        return photoRepository.getPhotoDetail(photoId)
    }

    suspend operator fun invoke(photoId: String) = doWork(photoId)
}