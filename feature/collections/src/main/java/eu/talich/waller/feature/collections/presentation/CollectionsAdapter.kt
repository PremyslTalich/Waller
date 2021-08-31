package eu.talich.waller.feature.collections.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.feature.collections.databinding.CollectionsItemCollectionBinding

class CollectionsAdapter(
    private var collections: List<CollectionVo>,
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

    fun setCollections(collections: List<CollectionVo>) {
        this.collections = collections

        notifyDataSetChanged()
    }
}
