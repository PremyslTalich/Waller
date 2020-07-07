package eu.talich.domain.repository

import eu.talich.domain.model.Image

interface ImageRepository {
    suspend fun getImages(page: Int): List<Image>
}
