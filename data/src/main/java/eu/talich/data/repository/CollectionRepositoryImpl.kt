package eu.talich.data.repository

import eu.talich.data.source.CollectionsSource
import eu.talich.domain.model.Collection
import eu.talich.domain.model.Photo
import eu.talich.domain.repository.CollectionRepository

class CollectionRepositoryImpl(
    private val collectionsSource: CollectionsSource
) : CollectionRepository {
    override suspend fun getFeaturedCollections(page: Int): List<Collection> {
        return collectionsSource.getFeaturedCollections(page)
    }

    override suspend fun getCollectionPhotos(collectionId: String, page: Int): List<Photo> {
        return collectionsSource.getCollectionPhotos(collectionId, page)
    }
}