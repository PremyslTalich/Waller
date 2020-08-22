package eu.talich.waller.presentation.collectiondetail.vh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.R
import eu.talich.waller.presentation.common.model.PhotoVo
import eu.talich.waller.presentation.common.extension.loadPhoto
import kotlinx.android.synthetic.main.photos_item_image.view.*

class CollectionPhotoViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.photos_item_image, parent, false)) {
    fun bind(photoVo: PhotoVo) {
        itemView.description.text = photoVo.description ?: ""

        photoVo.smallUrl?.let { it ->
            itemView.image.loadPhoto(it)
        }
    }
}