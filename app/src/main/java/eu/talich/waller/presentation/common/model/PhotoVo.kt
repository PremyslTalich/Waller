package eu.talich.waller.presentation.common.model

import java.io.Serializable

data class PhotoVo(
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val color: String,
    val smallUrl: String?,
    val downloadUrl: String?,
    val fullUrl: String?
) : Serializable