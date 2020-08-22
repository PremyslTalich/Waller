package eu.talich.waller.presentation.common.model

data class PhotoVo(
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val smallUrl: String?,
    val downloadUrl: String?
)