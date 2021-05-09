package eu.talich.waller.feature.photos.presentation

sealed class AlertState

object None : AlertState()
object EmptySearch : AlertState()
object BadConnection : AlertState()
object NoInternet : AlertState()
