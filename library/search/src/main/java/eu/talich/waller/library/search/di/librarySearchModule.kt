package eu.talich.waller.library.search.di

import eu.talich.waller.library.search.data.SearchRepositoryImpl
import eu.talich.waller.library.search.domain.GetSearchQueryUseCase
import eu.talich.waller.library.search.domain.ObserveSearchQueryUseCase
import eu.talich.waller.library.search.domain.SearchRepository
import eu.talich.waller.library.search.domain.SetSearchQueryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val librarySearchModule = module {
    single<SearchRepository> { SearchRepositoryImpl() }

    factory { ObserveSearchQueryUseCase(get()) }
    factory { SetSearchQueryUseCase(get()) }
    factory { GetSearchQueryUseCase(get()) }
}