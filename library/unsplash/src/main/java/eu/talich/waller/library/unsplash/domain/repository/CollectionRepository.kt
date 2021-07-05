package eu.talich.waller.library.unsplash.domain.repository

import eu.talich.waller.library.unsplash.model.CollectionDto
import eu.talich.waller.library.unsplash.model.CollectionSearchResultDto
import eu.talich.waller.library.unsplash.model.PhotoDto


interface CollectionRepository {
    suspend fun getFeaturedCollections(page: Int): List<CollectionDto>

    suspend fun searchCollections(query: String, page: Int): CollectionSearchResultDto

    suspend fun getCollectionPhotos(collectionId: String, page: Int): List<PhotoDto>
}
