package eu.talich.domain.repository

import kotlinx.coroutines.flow.Flow

interface InternetConnectionRepository {
    suspend fun observeInternetConnection(): Flow<Boolean>
}