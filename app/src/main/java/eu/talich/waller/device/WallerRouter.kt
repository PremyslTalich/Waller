package eu.talich.waller.device

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import eu.talich.waller.R
import eu.talich.waller.common.navigation.model.*
import eu.talich.waller.feature.collectiondetail.system.CollectionDetailFragmentDirections
import eu.talich.waller.feature.main.system.MainFragmentDirections
import eu.talich.waller.feature.photodetail.system.PhotoDetailFragmentDirections
import eu.talich.waller.library.navigation.model.Router
import eu.talich.waller.library.navigation.model.RouterDestination

class WallerRouter : Router {
    private lateinit var navController: NavController

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun onNewDestination(targetDestination: RouterDestination) {
        when (targetDestination) {
            is Main ->
                getMainAction()

            is PhotoDetail ->
                getPhotoDetailAction(targetDestination)

            is CollectionDetail ->
                getCollectionDetailAction(targetDestination)

            is Search ->
                getSearchDialogAction()

            else -> null
        }?.let {
            navController.navigate(it)
        }
    }

    private fun getMainAction(): NavDirections? {
        return when (currentDestination()) {
            R.id.photoDetailFragment ->
                PhotoDetailFragmentDirections.actionPhotoDetailToMain()

            R.id.collectionDetailFragment ->
                CollectionDetailFragmentDirections.actionCollectionDetailToMain()

            else -> null
        }
    }

    private fun getPhotoDetailAction(photo: PhotoDetail): NavDirections? {
        return when (currentDestination()) {
            R.id.mainFragment ->
                MainFragmentDirections.actionMainToPhotoDetail(photo)

            R.id.collectionDetailFragment ->
                CollectionDetailFragmentDirections.actionCollectionDetailToPhotoDetail(photo)

            else -> null
        }
    }

    private fun getCollectionDetailAction(collection: CollectionDetail): NavDirections? {
        return when (currentDestination()) {
            R.id.mainFragment -> MainFragmentDirections.actionMainFragmentToCollectionDetailFragment(collection)

            else -> null
        }
    }

    private fun getSearchDialogAction(): NavDirections? {
        return when (currentDestination()) {
            R.id.mainFragment -> MainFragmentDirections.actionMainFragmentToSearchDialogFragment()

            else -> null
        }
    }

    private fun currentDestination() = navController.currentDestination?.id
}
