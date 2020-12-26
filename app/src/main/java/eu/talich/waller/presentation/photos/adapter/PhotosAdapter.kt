package eu.talich.waller.presentation.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.R
import eu.talich.waller.databinding.PhotosItemImageBinding
import eu.talich.waller.presentation.common.model.PhotoVo
import eu.talich.waller.presentation.photos.vh.PhotoViewHolder
import eu.talich.waller.presentation.common.adapter.InfiniteLoader
import eu.talich.waller.presentation.main.MainFragmentDirections

class PhotosAdapter(
    private var photos: MutableList<PhotoVo>,
    private val infiniteLoader: InfiniteLoader
): RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotosItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])

        holder.itemView.setOnClickListener {
            val args = MainFragmentDirections.actionMainFragmentToPhotoDetailFragment(photos[position])
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
