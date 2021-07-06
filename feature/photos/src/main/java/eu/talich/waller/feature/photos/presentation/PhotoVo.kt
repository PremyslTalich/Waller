package eu.talich.waller.feature.photos.presentation

data class PhotoVo(
    val id: String,
    val description: String?,
    val urlFull: String,
    val color: String,
    val blurHash: BlurHashVo?,
    val thumbnail: ThumbnailVo
)