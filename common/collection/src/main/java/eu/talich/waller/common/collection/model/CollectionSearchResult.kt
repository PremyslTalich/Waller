package eu.talich.waller.common.collection.model

data class CollectionSearchResult(
    val total: Int,
    val totalPages: Int,
    val results: List<Collection>
)
