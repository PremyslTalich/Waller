package eu.talich.data.repository

import eu.talich.data.source.PhotoSource
import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoDetail
import eu.talich.domain.model.PhotoSearchResult
import eu.talich.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoSource: PhotoSource
) : PhotoRepository {
    override suspend fun getPhotos(page: Int): List<Photo> {
        return photoSource.getPhotos(page)
    }

    override suspend fun searchPhotos(query: String, page: Int): PhotoSearchResult {
        return photoSource.searchPhotos(query, page)
    }

    override suspend fun getPhotoDetail(photoId: String): PhotoDetail {
        return photoSource.getPhotoDetail(photoId)
    }
}
