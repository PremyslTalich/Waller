package eu.talich.waller.common.navigation.model

import eu.talich.waller.library.navigation.model.RouterDestination
import java.io.Serializable

sealed class WallerRouterDestinations {
    object Main : RouterDestination

    object Search : RouterDestination

    data class PhotoDetail(
        val id: String,
        val url: String,
        val color: String,
        val blurHash: BlurHash?
    ) : RouterDestination, Serializable {
        data class BlurHash(
            val hash: String,
            val width: Int,
            val height: Int
        ) : Serializable
    }

    object CollectionDetail : RouterDestination
}