package eu.talich.data.di

import eu.talich.data.repository.ImageRepositoryImpl
import eu.talich.domain.repository.ImageRepository
import org.koin.dsl.module

val dataModule = module {
    factory<ImageRepository> { ImageRepositoryImpl(get()) }
}