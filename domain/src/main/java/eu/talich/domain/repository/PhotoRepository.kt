package eu.talich.domain.repository

import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoDetail

interface PhotoRepository {
    suspend fun getPhotos(page: Int): List<Photo>

    suspend fun getPhotoDetail(photoId: String): PhotoDetail
}
