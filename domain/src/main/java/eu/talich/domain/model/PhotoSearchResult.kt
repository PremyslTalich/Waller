package eu.talich.domain.model

data class PhotoSearchResult(
    val total: Int,
    val totalPages: Int,
    val results: List<Photo>
)
