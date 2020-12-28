package eu.talich.domain.usecase

import eu.talich.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetSearchQueryUseCase(
    private val searchRepository: SearchRepository
) {
    private suspend fun doWork(): Flow<String?> {
        return searchRepository.getSearchQuery()
    }

    suspend operator fun invoke() = doWork()
}