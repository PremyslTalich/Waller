package eu.talich.waller.library.internetobserver.domain

import kotlinx.coroutines.flow.Flow

interface InternetConnectionController {
    suspend fun observeInternetConnection(): Flow<Boolean>
}