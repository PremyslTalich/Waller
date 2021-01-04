package eu.talich.data.source

import kotlinx.coroutines.flow.Flow

interface InternetConnectionSource {
    suspend fun observeInternetConnection(): Flow<Boolean>
}
