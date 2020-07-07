package eu.talich.waller.presentation.main.vh

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eu.talich.waller.R
import eu.talich.waller.presentation.main.model.ImageVo
import kotlinx.android.synthetic.main.main_item_image.view.*

class ImageViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.main_item_image, parent, false)) {
    fun bind(image: ImageVo) {
        itemView.description.text = image.description ?: ""

        image.smallUrl?.let { it ->
            Glide.with(itemView.image.context).load(it).into(itemView.image)
        }
    }
}