package eu.talich.waller.feature.collectiondetail.system

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.collectiondetail.databinding.PhotosItemImageBinding
import eu.talich.waller.feature.collectiondetail.presentation.PhotoVo

class PhotosAdapter(
    private var photos: MutableList<PhotoVo>,
    private val loadMoreCollectionPhotos: () -> Unit,
    private val openPhotoDetail: (id: String, url: String, color: String) -> Unit
): RecyclerView.Adapter<CollectionPhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionPhotoViewHolder {
        val binding = PhotosItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionPhotoViewHolder, position: Int) {
        val photo = photos[position]

        holder.bind(photo)

        holder.itemView.setOnClickListener {
            openPhotoDetail(
                photo.id,
                photo.url,
                photo.color
            )
        }

        if (position == itemCount - 1) {
            loadMoreCollectionPhotos()
        }
    }

    override fun getItemCount(): Int = photos.size

    fun addPhotos(newPhotos: List<PhotoVo>) {
        val startIndex = itemCount
        photos.addAll(newPhotos)
        notifyItemRangeInserted(startIndex, newPhotos.size)
    }
}
