package eu.talich.infrastructure.source

import eu.talich.data.source.PhotoSource
import eu.talich.domain.model.Photo
import eu.talich.infrastructure.model.unsplash.mapper.PhotoMapper
import eu.talich.infrastructure.network.unsplash.UnsplashApi

class PhotoSourceImpl(
    private val unsplashApi: UnsplashApi,
    private val photoMapper: PhotoMapper
) : PhotoSource {
    override suspend fun getPhotos(page: Int): List<Photo> {
        return unsplashApi.getPhotos(page).map {
            photoMapper.map(it)
        }
    }
}