package eu.talich.waller.library.navigation.model

interface Router {
    fun onNewDestination(targetDestination: RouterDestination)
}