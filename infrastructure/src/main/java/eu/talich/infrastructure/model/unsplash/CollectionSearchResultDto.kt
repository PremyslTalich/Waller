package eu.talich.infrastructure.model.unsplash

import com.squareup.moshi.Json

data class CollectionSearchResultDto(
    val total: Int,
    @Json(name = "total_pages") val totalPages: Int,
    val results: List<CollectionDto>
)
