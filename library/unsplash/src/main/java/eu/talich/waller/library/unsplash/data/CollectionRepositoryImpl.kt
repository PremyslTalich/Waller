package eu.talich.waller.library.unsplash.data

import eu.talich.waller.library.unsplash.data.api.UnsplashApi
import eu.talich.waller.library.unsplash.domain.repository.CollectionRepository
import eu.talich.waller.library.unsplash.model.CollectionDto
import eu.talich.waller.library.unsplash.model.CollectionSearchResultDto
import eu.talich.waller.library.unsplash.model.PhotoDto


class CollectionRepositoryImpl(
    private val unsplashApi: UnsplashApi
) : CollectionRepository {
    override suspend fun getFeaturedCollections(page: Int): List<CollectionDto> {
        return unsplashApi.getFeaturedCollections(page)
    }

    override suspend fun searchCollections(query: String, page: Int): CollectionSearchResultDto {
        return unsplashApi.searchCollections(query, page)
    }

    override suspend fun getCollectionPhotos(collectionId: String, page: Int): List<PhotoDto> {
        return unsplashApi.getCollectionPhotos(collectionId, page)
    }
}
