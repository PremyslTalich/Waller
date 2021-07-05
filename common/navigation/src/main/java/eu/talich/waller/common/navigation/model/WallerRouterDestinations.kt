package eu.talich.waller.common.navigation.model

import eu.talich.waller.library.navigation.model.RouterDestination
import java.io.Serializable

sealed class WallerRouterDestinations : RouterDestination

object Main : WallerRouterDestinations()

object Search : WallerRouterDestinations()

data class PhotoDetail(
    val id: String,
    val url: String,
    val color: String,
    val blurHash: BlurHash?
) : WallerRouterDestinations(), Serializable {
    data class BlurHash(
        val hash: String,
        val width: Int,
        val height: Int
    ) : Serializable
}

data class CollectionDetail(
    val id: String,
    val title: String,
    val description: String,
    val coverPhoto: CoverPhoto?
) : WallerRouterDestinations(), Serializable {
    data class CoverPhoto(
        val id: String,
        val url: String,
        val thumbnail: String,
        val color: String
    ) : Serializable
}