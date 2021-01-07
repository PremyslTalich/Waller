package eu.talich.waller.presentation.collections.vm

sealed class AlertState

object None : AlertState()
object EmptySearch : AlertState()
object BadConnection : AlertState()
object NoInternet : AlertState()
