package eu.talich.waller.presentation.collectiondetail.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.databinding.PhotosItemImageBinding
import eu.talich.waller.presentation.common.extension.loadPhoto
import eu.talich.waller.presentation.common.model.PhotoVo

class CollectionPhotoViewHolder(private val binding: PhotosItemImageBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(photoVo: PhotoVo) {
        photoVo.description?.let {
            binding.description.visibility = View.VISIBLE
            binding.description.text = it
        } ?: run {
            binding.description.visibility = View.GONE
        }

        binding.image.loadPhoto(photoVo.thumbnail.rawUrl, photoVo.color)
    }
}