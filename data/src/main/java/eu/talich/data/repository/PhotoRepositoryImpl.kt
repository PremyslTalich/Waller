package eu.talich.data.repository

import eu.talich.data.source.PhotoSource
import eu.talich.domain.model.Photo
import eu.talich.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoSource: PhotoSource
) : PhotoRepository {
    override suspend fun getPhotos(page: Int): List<Photo> {
        return photoSource.getPhotos(page)
    }
}