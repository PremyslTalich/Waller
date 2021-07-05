package eu.talich.waller.feature.collections.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.feature.collections.databinding.CollectionsItemCollectionBinding

class CollectionsAdapter(
    private var collections: MutableList<CollectionVo>,
    private val loadMoreCollections: () -> Unit,
    private val openCollectionDetail: (
        id: String,
        title: String,
        description: String,
        coverPhotoId: String,
        coverPhotoUrl: String,
        coverPhotoThumbnailUrl: String,
        coverPhotoColor: String,
    ) -> Unit
): RecyclerView.Adapter<CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = CollectionsItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val collection = collections[position]

        holder.bind(collection)

        holder.itemView.setOnClickListener {
            with(collection) {
                openCollectionDetail(
                    id,
                    title,
                    description,
                    coverPhoto.id,
                    coverPhoto.rawUrl,
                    coverPhoto.thumbnailUrl,
                    coverPhoto.color
                )
            }
        }

        if (position == itemCount - 1) {
            loadMoreCollections()
        }
    }

    override fun getItemCount(): Int = collections.size

    fun addCollections(newCollections: List<CollectionVo>) {
        val startIndex = itemCount
        collections.addAll(newCollections)
        notifyItemRangeInserted(startIndex, newCollections.size)
    }

    fun removeCollections() {
        val itemCount = itemCount

        collections.clear()
        notifyItemRangeRemoved(0, itemCount)
    }
}
