package eu.talich.waller.feature.photos.presentation

data class PhotosViewState(
    val photos: List<PhotoVo> = emptyList(),
    val alert: AlertState? = null,
    val loading: Boolean = false
) {
    enum class AlertState {
        EMPTY_SEARCH,
        BAD_CONNECTION,
        NO_INTERNET
    }
}