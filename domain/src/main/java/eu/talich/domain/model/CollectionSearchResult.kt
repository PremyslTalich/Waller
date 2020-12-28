package eu.talich.domain.model

data class CollectionSearchResult(
    val total: Int,
    val totalPages: Int,
    val results: List<Collection>
)
