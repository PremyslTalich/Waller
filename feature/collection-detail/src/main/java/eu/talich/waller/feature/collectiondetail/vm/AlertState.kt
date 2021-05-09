package eu.talich.waller.presentation.collectiondetail.vm

sealed class AlertState

object None : AlertState()
object BadConnection : AlertState()
object NoInternet : AlertState()
