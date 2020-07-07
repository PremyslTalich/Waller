package eu.talich.domain.usecase

import eu.talich.domain.model.Image
import eu.talich.domain.repository.ImageRepository


class GetImagesUseCase(
    private val imageRepository: ImageRepository
) {
    private suspend fun doWork(page: Int): List<Image> {
        return imageRepository.getImages(page)
    }

    suspend operator fun invoke(page: Int) = doWork(page)
}