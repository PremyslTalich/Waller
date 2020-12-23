package eu.talich.waller.presentation.common.model

import java.io.Serializable

data class CollectionVo (
    val id: String,
    val title: String,
    val description: String,
    val coverPhoto: PhotoVo?
) : Serializable