package eu.talich.waller.feature.collections.presentation

data class CollectionsViewState(
    val collections: List<CollectionVo> = emptyList(),
    val alert: AlertState? = null,
    val loading: Boolean = false
) {
    enum class AlertState {
        EMPTY_SEARCH,
        BAD_CONNECTION,
        NO_INTERNET
    }
}