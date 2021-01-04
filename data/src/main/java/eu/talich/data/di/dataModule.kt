package eu.talich.data.di

import eu.talich.data.repository.CollectionRepositoryImpl
import eu.talich.data.repository.InternetConnectionRepositoryImpl
import eu.talich.data.repository.PhotoRepositoryImpl
import eu.talich.data.repository.SearchRepositoryImpl
import eu.talich.domain.repository.CollectionRepository
import eu.talich.domain.repository.InternetConnectionRepository
import eu.talich.domain.repository.PhotoRepository
import eu.talich.domain.repository.SearchRepository
import org.koin.dsl.module

val dataModule = module {
    factory<PhotoRepository> { PhotoRepositoryImpl(get()) }
    factory<CollectionRepository> { CollectionRepositoryImpl(get()) }

    single<SearchRepository> { SearchRepositoryImpl() }

    single<InternetConnectionRepository> { InternetConnectionRepositoryImpl(get()) }
}