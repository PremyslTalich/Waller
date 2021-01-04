package eu.talich.domain.usecase

import eu.talich.domain.repository.InternetConnectionRepository
import kotlinx.coroutines.flow.Flow

class ObserveInternetConnectionUseCase(
    private val internetConnectionRepository: InternetConnectionRepository
) {
    private suspend fun doWork(): Flow<Boolean> {
        return internetConnectionRepository.observeInternetConnection()
    }

    suspend operator fun invoke() = doWork()
}