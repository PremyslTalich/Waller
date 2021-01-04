package eu.talich.data.repository

import eu.talich.data.source.InternetConnectionSource
import eu.talich.domain.repository.InternetConnectionRepository
import kotlinx.coroutines.flow.Flow

class InternetConnectionRepositoryImpl(
    private val internetConnectionSource: InternetConnectionSource
) : InternetConnectionRepository {
    override suspend fun observeInternetConnection(): Flow<Boolean> {
        return internetConnectionSource.observeInternetConnection()
    }
}