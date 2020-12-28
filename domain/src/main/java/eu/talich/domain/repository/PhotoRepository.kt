package eu.talich.domain.repository

import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoDetail
import eu.talich.domain.model.PhotoSearchResult

interface PhotoRepository {
    suspend fun getPhotos(page: Int): List<Photo>

    suspend fun searchPhotos(query: String, page: Int): PhotoSearchResult

    suspend fun getPhotoDetail(photoId: String): PhotoDetail
}
