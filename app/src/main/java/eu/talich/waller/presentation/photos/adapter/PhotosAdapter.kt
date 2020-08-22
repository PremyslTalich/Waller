package eu.talich.waller.presentation.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.presentation.common.model.PhotoVo
import eu.talich.waller.presentation.photos.vh.PhotoViewHolder
import eu.talich.waller.presentation.common.adapter.InfiniteLoader

class PhotosAdapter(
    private var photos: MutableList<PhotoVo>,
    private val infiniteLoader: InfiniteLoader
): RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])

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
