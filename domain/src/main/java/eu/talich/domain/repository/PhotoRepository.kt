package eu.talich.domain.repository

import eu.talich.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(page: Int): List<Photo>
}
