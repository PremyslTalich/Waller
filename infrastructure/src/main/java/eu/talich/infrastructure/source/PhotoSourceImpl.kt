package eu.talich.infrastructure.source

import eu.talich.data.source.PhotoSource
import eu.talich.domain.model.Photo
import eu.talich.domain.model.PhotoDetail
import eu.talich.domain.model.PhotoSearchResult
import eu.talich.infrastructure.model.unsplash.mapper.PhotoDetailMapper
import eu.talich.infrastructure.model.unsplash.mapper.PhotoMapper
import eu.talich.infrastructure.model.unsplash.mapper.PhotoSearchResultMapper
import eu.talich.infrastructure.network.unsplash.UnsplashApi

class PhotoSourceImpl(
    private val unsplashApi: UnsplashApi,
    private val photoMapper: PhotoMapper,
    private val photoDetailMapper: PhotoDetailMapper,
    private val photoSearchResultMapper: PhotoSearchResultMapper
) : PhotoSource {
    override suspend fun getPhotos(page: Int): List<Photo> {
        return unsplashApi.getPhotos(page).map {
            photoMapper.map(it)
        }
    }

    override suspend fun searchPhotos(query: String, page: Int): PhotoSearchResult {
        return photoSearchResultMapper.map(unsplashApi.searchPhotos(query, page))
    }

    override suspend fun getPhotoDetail(photoId: String): PhotoDetail {
        return photoDetailMapper.map(unsplashApi.getPhotoDetail(photoId))
    }
}