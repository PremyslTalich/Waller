package eu.talich.waller.library.unsplash.domain.repository

import eu.talich.waller.library.unsplash.model.PhotoDetailDto
import eu.talich.waller.library.unsplash.model.PhotoDto
import eu.talich.waller.library.unsplash.model.PhotoSearchResultDto


interface PhotoRepository {
    suspend fun getPhotos(page: Int): List<PhotoDto>

    suspend fun searchPhotos(query: String, page: Int): PhotoSearchResultDto

    suspend fun getPhotoDetail(photoId: String): PhotoDetailDto
}
