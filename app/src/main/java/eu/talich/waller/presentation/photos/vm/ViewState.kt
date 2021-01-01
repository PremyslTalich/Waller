package eu.talich.waller.presentation.photos.vm

sealed class ViewState

object Init : ViewState()
object HasPhotos : ViewState()
data class EmptySearch(val vectorResId: Int, val messageResId: Int) : ViewState()
data class HasNoInternet(val vectorResId: Int, val messageResId: Int) : ViewState()
