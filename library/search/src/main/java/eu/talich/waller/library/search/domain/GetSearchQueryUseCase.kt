package eu.talich.waller.library.search.domain

class GetSearchQueryUseCase(
    private val searchRepository: SearchRepository
) {
    private fun doWork(): String? {
        return searchRepository.getSearchQuery()
    }

    operator fun invoke(): String? = doWork()
}