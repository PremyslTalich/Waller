package eu.talich.domain.di

import eu.talich.domain.usecase.*
import org.koin.dsl.module


val domainModule = module {
    factory { GetPhotosUseCase(get()) }
    factory { SearchPhotosUseCase(get()) }
    factory { GetPhotoDetailUseCase(get()) }
    factory { GetFeaturedCollectionsUseCase(get()) }
    factory { SearchCollectionsUseCase(get()) }
    factory { GetCollectionPhotosUseCase(get()) }
    factory { GetSearchQueryUseCase(get()) }
    factory { SetSearchQueryUseCase(get()) }
}