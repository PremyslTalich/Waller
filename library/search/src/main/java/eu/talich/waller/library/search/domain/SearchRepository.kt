package eu.talich.waller.library.search.domain

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun setSearchQuery(newSearchQuery: String?)

    fun getSearchQuery(): String?

    suspend fun observeSearchQuery(): Flow<String?>
}