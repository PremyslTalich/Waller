package eu.talich.waller.library.search.domain

import kotlinx.coroutines.flow.Flow

class ObserveSearchQueryUseCase(
    private val searchRepository: SearchRepository
) {
    private suspend fun doWork(): Flow<String?> {
        return searchRepository.observeSearchQuery()
    }

    suspend operator fun invoke() = doWork()
}