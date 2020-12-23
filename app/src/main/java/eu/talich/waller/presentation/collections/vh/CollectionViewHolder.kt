package eu.talich.waller.presentation.collections.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.R
import eu.talich.waller.presentation.common.model.CollectionVo
import eu.talich.waller.presentation.common.extension.loadPhoto
import kotlinx.android.synthetic.main.collections_item_collection.view.*

class CollectionViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.collections_item_collection, parent, false)) {
    fun bind(collectionVo: CollectionVo) {
        itemView.title.text = collectionVo.title

        collectionVo.coverPhoto?.smallUrl?.let { it ->
            itemView.coverPhoto.loadPhoto(it)
        }
    }
}