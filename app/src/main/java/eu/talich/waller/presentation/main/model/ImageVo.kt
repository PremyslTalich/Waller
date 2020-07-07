package eu.talich.waller.presentation.main.model

data class ImageVo(
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val smallUrl: String?,
    val downloadUrl: String?
)