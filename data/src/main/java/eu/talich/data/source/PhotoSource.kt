package eu.talich.data.source

import eu.talich.domain.model.Photo

interface PhotoSource {
    suspend fun getPhotos(page: Int = 1): List<Photo>
}