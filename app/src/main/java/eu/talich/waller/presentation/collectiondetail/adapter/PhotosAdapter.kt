package eu.talich.waller.presentation.collectiondetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.presentation.collectiondetail.CollectionDetailFragmentDirections
import eu.talich.waller.presentation.collectiondetail.vh.CollectionPhotoViewHolder
import eu.talich.waller.presentation.common.adapter.InfiniteLoader
import eu.talich.waller.presentation.common.model.PhotoVo

class PhotosAdapter(
    private var photos: MutableList<PhotoVo>,
    private val infiniteLoader: InfiniteLoader
): RecyclerView.Adapter<CollectionPhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionPhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CollectionPhotoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CollectionPhotoViewHolder, position: Int) {
        holder.bind(photos[position])

        holder.itemView.setOnClickListener {
            val args = CollectionDetailFragmentDirections.actionCollectionDetailFragmentToPhotoDetailFragment(photos[position])
            it.findNavController().navigate(args)
        }

        if (position == itemCount - 1) {
            infiniteLoader.loadMore()
        }
    }

    override fun getItemCount(): Int = photos.size

    fun addPhotos(newPhotos: List<PhotoVo>) {
        val startIndex = itemCount
        photos.addAll(newPhotos)
        notifyItemRangeInserted(startIndex, newPhotos.size)
    }
}
