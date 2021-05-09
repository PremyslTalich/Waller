package eu.talich.waller.common.photo.model

data class PhotoSearchResult(
    val total: Int,
    val totalPages: Int,
    val results: List<Photo>
)
