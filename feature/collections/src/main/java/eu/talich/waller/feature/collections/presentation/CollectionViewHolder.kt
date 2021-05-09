package eu.talich.waller.feature.collections.presentation

import androidx.recyclerview.widget.RecyclerView

class CollectionViewHolder(private val binding: CollectionsItemCollectionBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(collectionVo: CollectionVo) {
        binding.title.text = collectionVo.title
        binding.totalPhotos.text = collectionVo.totalPhotos.toString()

        collectionVo.coverPhoto?.let { it ->
            binding.coverPhoto.loadPhoto(it.thumbnail.rawUrl)
        }
    }
}