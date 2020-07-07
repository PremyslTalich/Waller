package eu.talich.infrastructure.source

import eu.talich.data.source.ImageSource
import eu.talich.domain.model.Image
import eu.talich.domain.model.ImageUrls
import eu.talich.infrastructure.network.unsplash.UnsplashApi

class ImageSourceImpl(
    private val unsplashApi: UnsplashApi
) : ImageSource {
    override suspend fun getImages(page: Int): List<Image> {
        return unsplashApi.getImages(page).map {
            Image(
                it.id,
                it.description,
                it.width,
                it.height,
                ImageUrls(
                    it.urls?.raw,
                    it.urls?.full,
                    it.urls?.regular,
                    it.urls?.small,
                    it.urls?.thumb
                )
            )
        }
    }
}