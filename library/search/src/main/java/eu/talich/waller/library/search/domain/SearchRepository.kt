package eu.talich.waller.library.search.domain

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun setSearchQuery(newSearchQuery: String?)

    suspend fun observeSearchQuery(): Flow<String?>
}