package eu.talich.waller.presentation.photos.vm

sealed class AlertState

object None : AlertState()
object EmptySearch : AlertState()
object BadConnection : AlertState()
object NoInternet : AlertState()
