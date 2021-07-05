package eu.talich.waller.library.navigation.domain.controller

import eu.talich.waller.library.navigation.model.RouterDestination

interface RouterController {
    suspend fun navigate(destination: RouterDestination)
}