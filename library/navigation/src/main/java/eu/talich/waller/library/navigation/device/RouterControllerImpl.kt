package eu.talich.waller.library.navigation.device

import eu.talich.waller.library.navigation.model.Router
import eu.talich.waller.library.navigation.domain.controller.RouterController
import eu.talich.waller.library.navigation.model.RouterDestination

class RouterControllerImpl(
    private val router: Router
) : RouterController {
    override suspend fun navigate(destination: RouterDestination) {
        router.onNewDestination(destination)
    }
}