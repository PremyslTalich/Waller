package eu.talich.waller.feature.collections.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.feature.collections.model.CollectionVo

class CollectionsAdapter(
    private var collections: MutableList<CollectionVo>,
    private val loadMoreCollections: () -> Unit
): RecyclerView.Adapter<CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = CollectionsItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val collection = collections[position]

        holder.bind(collection)

        holder.itemView.setOnClickListener {
            val args = MainFragmentDirections.actionMainFragmentToCollectionDetailFragment(collection)
            it.findNavController().navigate(args)
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
        collections.clear()
        notifyDataSetChanged()
    }
}
