package eu.talich.waller.presentation.common.model

import java.io.Serializable

data class PhotoVo(
    val id: String,
    val description: String?,
    val width: Int,
    val height: Int,
    val color: String,
    val downloadUrl: String,
    val fullSizeUrl: String,
    val blurHash: BlurHashVo?,
    val thumbnail: ThumbnailVo
) : Serializable