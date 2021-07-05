package eu.talich.waller.library.internetobserver.domain

import kotlinx.coroutines.flow.Flow

class ObserveInternetConnectionUseCase(
    private val internetConnectionController: InternetConnectionController
) {
    private suspend fun doWork(): Flow<Boolean> {
        return internetConnectionController.observeInternetConnection()
    }

    suspend operator fun invoke() = doWork()
}
