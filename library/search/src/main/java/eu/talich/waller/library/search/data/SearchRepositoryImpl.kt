package eu.talich.waller.library.search.data

import eu.talich.waller.library.search.domain.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ExperimentalCoroutinesApi

@FlowPreview
@ExperimentalCoroutinesApi
class SearchRepositoryImpl : SearchRepository {
    private val searchQuery = ConflatedBroadcastChannel<String?>()

    override suspend fun setSearchQuery(newSearchQuery: String?) {
        if (searchQuery.valueOrNull != newSearchQuery) {
            searchQuery.send(newSearchQuery)
        }
    }

    override fun getSearchQuery(): String? {
        return searchQuery.valueOrNull
    }

    override suspend fun observeSearchQuery(): Flow<String?> {
        return searchQuery.asFlow()
    }
}
