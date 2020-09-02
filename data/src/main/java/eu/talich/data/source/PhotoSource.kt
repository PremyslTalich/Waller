package eu.talich.data.source

import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoDetail

interface PhotoSource {
    suspend fun getPhotos(page: Int = 1): List<Photo>

    suspend fun getPhotoDetail(photoId: String): PhotoDetail
}