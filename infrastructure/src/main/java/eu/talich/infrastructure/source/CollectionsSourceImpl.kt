package eu.talich.infrastructure.source

import eu.talich.data.source.CollectionsSource
import eu.talich.domain.model.Collection
import eu.talich.domain.model.Photo
import eu.talich.infrastructure.model.unsplash.mapper.CollectionMapper
import eu.talich.infrastructure.model.unsplash.mapper.PhotoMapper
import eu.talich.infrastructure.network.unsplash.UnsplashApi

class CollectionsSourceImpl(
    private val unsplashApi: UnsplashApi,
    private val collectionMapper: CollectionMapper,
    private val photoMapper: PhotoMapper
) : CollectionsSource {
    override suspend fun getFeaturedCollections(page: Int): List<Collection> {
        return unsplashApi.getFeaturedCollections(page).map {
            collectionMapper.map(it)
        }
    }

    override suspend fun getCollectionPhotos(collectionId: String, page: Int): List<Photo> {
        return unsplashApi.getCollectionPhotos(collectionId, page).map {
            photoMapper.map(it)
        }
    }
}