package eu.talich.waller.feature.collections.presentation

sealed class AlertState

object None : AlertState()
object EmptySearch : AlertState()
object BadConnection : AlertState()
object NoInternet : AlertState()
