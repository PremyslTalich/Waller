package eu.talich.domain.usecase

import eu.talich.domain.model.PhotoDetail
import eu.talich.domain.repository.PhotoRepository

class GetPhotoDetailUseCase(
    private val photoRepository: PhotoRepository
) {
    private suspend fun doWork(photoId: String): PhotoDetail {
        return photoRepository.getPhotoDetail(photoId)
    }

    suspend operator fun invoke(photoId: String) = doWork(photoId)
}