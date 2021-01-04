package eu.talich.waller.presentation.photos.vm

sealed class ViewState

object Init : ViewState()
object HasPhotos : ViewState()
object EmptySearch : ViewState()
object BadConnection : ViewState()
object NoInternet : ViewState()
