package eu.talich.waller.presentation.collections.vm

sealed class ViewState

object Init : ViewState()
object HasCollections : ViewState()
data class EmptySearch(val vectorResId: Int, val messageResId: Int) : ViewState()
data class HasNoInternet(val vectorResId: Int, val messageResId: Int) : ViewState()
