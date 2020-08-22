package eu.talich.data.di

import eu.talich.data.repository.CollectionRepositoryImpl
import eu.talich.data.repository.PhotoRepositoryImpl
import eu.talich.domain.repository.CollectionRepository
import eu.talich.domain.repository.PhotoRepository
import org.koin.dsl.module

val dataModule = module {
    factory<PhotoRepository> { PhotoRepositoryImpl(get()) }
    factory<CollectionRepository> { CollectionRepositoryImpl(get()) }
}