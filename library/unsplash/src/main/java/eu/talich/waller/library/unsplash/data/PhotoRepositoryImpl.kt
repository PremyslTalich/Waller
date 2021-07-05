package eu.talich.waller.library.unsplash.data

import eu.talich.waller.library.unsplash.data.api.UnsplashApi
import eu.talich.waller.library.unsplash.domain.repository.PhotoRepository
import eu.talich.waller.library.unsplash.model.PhotoDetailDto
import eu.talich.waller.library.unsplash.model.PhotoDto
import eu.talich.waller.library.unsplash.model.PhotoSearchResultDto

class PhotoRepositoryImpl(
    private val unsplashApi: UnsplashApi,
) : PhotoRepository {
    override suspend fun getPhotos(page: Int): List<PhotoDto> {
        return unsplashApi.getPhotos(page)
    }

    override suspend fun searchPhotos(query: String, page: Int): PhotoSearchResultDto {
        return unsplashApi.searchPhotos(query, page)
    }

    override suspend fun getPhotoDetail(photoId: String): PhotoDetailDto {
        return unsplashApi.getPhotoDetail(photoId)
    }
}