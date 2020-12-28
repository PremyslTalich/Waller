package eu.talich.data.source

import eu.talich.domain.model.Collection
import eu.talich.domain.model.CollectionSearchResult
import eu.talich.domain.model.Photo

interface CollectionsSource {
    suspend fun getFeaturedCollections(page: Int = 1): List<Collection>

    suspend fun searchCollections(query: String, page: Int): CollectionSearchResult

    suspend fun getCollectionPhotos(collectionId: String, page: Int): List<Photo>
}