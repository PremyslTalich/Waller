package eu.talich.waller.library.search.domain

class SetSearchQueryUseCase(
    private val searchRepository: SearchRepository
) {
    private suspend fun doWork(newSearchQuery: String?) {
        return searchRepository.setSearchQuery(newSearchQuery)
    }

    suspend operator fun invoke(newSearchQuery: String?) = doWork(newSearchQuery)
}