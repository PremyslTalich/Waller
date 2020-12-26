package eu.talich.waller.presentation.photos.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.databinding.PhotosItemImageBinding
import eu.talich.waller.presentation.common.extension.loadPhoto
import eu.talich.waller.presentation.common.model.PhotoVo

class PhotoViewHolder(private val binding: PhotosItemImageBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(photoVo: PhotoVo) {
        photoVo.description?.let {
            binding.description.visibility = View.VISIBLE
            binding.description.text = it
        } ?: run {
            binding.description.visibility = View.GONE
        }

        photoVo.smallUrl?.let { it ->
            binding.image.loadPhoto(it, photoVo.color)
        }
    }
}