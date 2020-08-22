package eu.talich.domain.di

import eu.talich.domain.usecase.GetCollectionPhotosUseCase
import eu.talich.domain.usecase.GetFeaturedCollectionsUseCase
import eu.talich.domain.usecase.GetPhotosUseCase
import org.koin.dsl.module


val domainModule = module {
    factory { GetPhotosUseCase(get()) }
    factory { GetFeaturedCollectionsUseCase(get()) }
    factory { GetCollectionPhotosUseCase(get()) }
}