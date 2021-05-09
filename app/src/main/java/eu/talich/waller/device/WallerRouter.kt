package eu.talich.waller.device

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import eu.talich.waller.common.navigation.model.WallerRouterDestinations
import eu.talich.waller.library.navigation.model.Router
import eu.talich.waller.library.navigation.model.RouterDestination
import eu.talich.waller.R

class WallerRouter(
    private val navController: NavController
) : Router {
    override fun onNewDestination(targetDestination: RouterDestination) {
        when (targetDestination) {
            is WallerRouterDestinations.PhotoDetail ->
                getPhotoDetailAction(targetDestination)

            is WallerRouterDestinations.CollectionDetail ->
                getCollectionDetailAction()

            is WallerRouterDestinations.Search ->
                getSearchDialogAction()

            else -> null
        }?.let {
            navController.navigate(it)
        }
    }

    private fun getPhotoDetailAction(photo: WallerRouterDestinations.PhotoDetail): NavDirections? {
        return when (currentDestination()) {
            R.id.mainFragment ->
                MainFragmentDirections.actionMainFragmentToPhotoDetailFragment(photo)

            else -> null
        }
    }

    private fun getCollectionDetailAction(): NavDirections? {
        return when (currentDestination()) {
            R.id.mainFragment -> TODO()
            R.id.collectionDetailFragment -> TODO()
            else -> null
        }
    }

    private fun getSearchDialogAction(): NavDirections? {
        return when (currentDestination()) {
            R.id.mainFragment -> TODO()
            else -> null
        }
    }

    private fun currentDestination() = navController.currentDestination?.id
}