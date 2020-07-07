package eu.talich.data.repository

import eu.talich.data.source.ImageSource
import eu.talich.domain.model.Image
import eu.talich.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val imageSource: ImageSource
) : ImageRepository {
    override suspend fun getImages(page: Int): List<Image> {
        return imageSource.getImages(page)
    }
}