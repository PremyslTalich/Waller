package eu.talich.waller.presentation.collectiondetail.vh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.R
import eu.talich.waller.presentation.common.extension.loadPhoto
import eu.talich.waller.presentation.common.model.PhotoVo
import kotlinx.android.synthetic.main.photos_item_image.view.*

class CollectionPhotoViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.photos_item_image, parent, false)) {
    fun bind(photoVo: PhotoVo) {
        photoVo.description?.let {
            itemView.description.visibility = View.VISIBLE
            itemView.description.text = it
        } ?: run {
            itemView.description.visibility = View.GONE
        }

        photoVo.smallUrl?.let { it ->
            itemView.image.loadPhoto(it, photoVo.color)
        }
    }
}