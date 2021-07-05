package eu.talich.waller.feature.collections.presentation

import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.common.ui.system.loadPhoto
import eu.talich.waller.feature.collections.databinding.CollectionsItemCollectionBinding

class CollectionViewHolder(private val binding: CollectionsItemCollectionBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(collectionVo: CollectionVo) {
        binding.title.text = collectionVo.title
        binding.totalPhotos.text = collectionVo.totalPhotos.toString()

        binding.coverPhoto.loadPhoto(collectionVo.coverPhoto.thumbnailUrl)
    }
}