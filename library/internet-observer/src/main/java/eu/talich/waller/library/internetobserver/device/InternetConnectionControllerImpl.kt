package eu.talich.waller.library.internetobserver.device

import eu.talich.waller.library.internetobserver.domain.InternetConnectionController
import kotlinx.coroutines.flow.Flow

class InternetConnectionControllerImpl(
    private val internetConnectivityManager: InternetConnectivityManager
) : InternetConnectionController {
    override suspend fun observeInternetConnection(): Flow<Boolean> {
        return internetConnectivityManager.observeInternetConnection()
    }
}