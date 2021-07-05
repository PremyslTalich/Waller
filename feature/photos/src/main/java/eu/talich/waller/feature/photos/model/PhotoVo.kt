package eu.talich.waller.feature.photos.model

data class PhotoVo(
    val id: String,
    val description: String?,
    val urlFull: String,
    val color: String,
    val blurHash: BlurHashVo?,
    val thumbnail: ThumbnailVo
)