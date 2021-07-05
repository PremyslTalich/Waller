package eu.talich.waller.feature.collectiondetail.system

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.collectiondetail.databinding.PhotosItemImageBinding
import eu.talich.waller.common.ui.system.loadPhoto
import eu.talich.waller.feature.collectiondetail.presentation.PhotoVo

class CollectionPhotoViewHolder(private val binding: PhotosItemImageBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(photoVo: PhotoVo) {
        photoVo.description?.let {
            binding.description.visibility = View.VISIBLE
            binding.description.text = it
        } ?: run {
            binding.description.visibility = View.GONE
        }

        binding.image.loadPhoto(photoVo.thumbnail, photoVo.color)
    }
}