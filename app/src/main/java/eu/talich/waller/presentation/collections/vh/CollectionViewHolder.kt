package eu.talich.waller.presentation.collections.vh

import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.databinding.CollectionsItemCollectionBinding
import eu.talich.waller.presentation.common.extension.loadPhoto
import eu.talich.waller.presentation.common.model.CollectionVo

class CollectionViewHolder(private val binding: CollectionsItemCollectionBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(collectionVo: CollectionVo) {
        binding.title.text = collectionVo.title

        collectionVo.coverPhoto?.smallUrl?.let { it ->
            binding.coverPhoto.loadPhoto(it)
        }
    }
}