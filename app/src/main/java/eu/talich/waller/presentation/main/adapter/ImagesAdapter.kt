package eu.talich.waller.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.presentation.main.model.ImageVo
import eu.talich.waller.presentation.main.vh.ImageViewHolder
import kotlinx.android.synthetic.main.main_item_image.view.*

class ImagesAdapter(
    private var images: MutableList<ImageVo>,
    private val infiniteLoader: InfiniteLoader
): RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]

        holder.bind(image)

//        if (position == itemCount - 1) {
//            infiniteLoader.loadMore()
//        }

        holder.itemView.container.viewTreeObserver.addOnGlobalLayoutListener {
            val ratio = image.width.toDouble() / image.height.toDouble()
            val density = holder.itemView.context.resources.displayMetrics.density

            Log.i("tady", "${image.id} $ratio $density ${holder.itemView.container.width} ${holder.itemView.container.measuredWidth}")

            holder.itemView.image.layoutParams.height = (holder.itemView.container.width * ratio * density).toInt()
//            holder.itemView.image.requestLayout()
        }
    }

    override fun getItemCount(): Int = images.size

    fun setImages(newImages: List<ImageVo>) {
        images = newImages.toMutableList()
        notifyDataSetChanged()
    }

    fun addImages(newImages: List<ImageVo>) {
        images.addAll(newImages)
        notifyDataSetChanged()
    }
}
