package eu.talich.waller.library.navigation.domain

import eu.talich.waller.library.navigation.domain.controller.RouterController
import eu.talich.waller.library.navigation.model.RouterDestination

class NavigateUseCase(
    private val routerController: RouterController
) {
    private suspend fun doWork(destination: RouterDestination) {
        routerController.navigate(destination)
    }

    suspend operator fun invoke(destination: RouterDestination) = doWork(destination)
}