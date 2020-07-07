package eu.talich.data.source

import eu.talich.domain.model.Image

interface ImageSource {
    suspend fun getImages(page: Int = 1): List<Image>
}