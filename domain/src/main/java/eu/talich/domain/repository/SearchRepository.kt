package eu.talich.domain.repository

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun setSearchQuery(newSearchQuery: String?)

    suspend fun getSearchQuery(): Flow<String?>
}