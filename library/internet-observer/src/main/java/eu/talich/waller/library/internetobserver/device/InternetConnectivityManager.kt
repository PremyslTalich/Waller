package eu.talich.waller.library.internetobserver.device

import kotlinx.coroutines.flow.Flow

interface InternetConnectivityManager {
    suspend fun observeInternetConnection(): Flow<Boolean>
}
