package eu.talich.data.source

import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoDetail
import eu.talich.domain.model.PhotoSearchResult

interface PhotoSource {
    suspend fun getPhotos(page: Int = 1): List<Photo>

    suspend fun searchPhotos(query: String, page: Int = 1): PhotoSearchResult

    suspend fun getPhotoDetail(photoId: String): PhotoDetail
}