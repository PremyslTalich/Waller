package eu.talich.waller.presentation.collections.vm

sealed class ViewState

object Init : ViewState()
object HasCollections : ViewState()
object EmptySearch : ViewState()
object BadConnection : ViewState()
object NoInternet : ViewState()
