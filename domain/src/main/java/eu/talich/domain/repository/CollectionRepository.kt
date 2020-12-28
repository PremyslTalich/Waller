package eu.talich.domain.repository

import eu.talich.domain.model.Collection
import eu.talich.domain.model.CollectionSearchResult
import eu.talich.domain.model.Photo

interface CollectionRepository {
    suspend fun getFeaturedCollections(page: Int): List<Collection>

    suspend fun searchCollections(query: String, page: Int): CollectionSearchResult

    suspend fun getCollectionPhotos(collectionId: String, page: Int): List<Photo>
}
