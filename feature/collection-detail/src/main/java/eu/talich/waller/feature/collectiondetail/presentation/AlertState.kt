package eu.talich.waller.feature.collectiondetail.presentation

sealed class AlertState

object None : AlertState()
object BadConnection : AlertState()
object NoInternet : AlertState()
