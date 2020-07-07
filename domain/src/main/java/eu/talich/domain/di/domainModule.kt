package eu.talich.domain.di

import eu.talich.domain.usecase.GetImagesUseCase
import org.koin.dsl.module


val domainModule = module {
    factory { GetImagesUseCase(get()) }
}