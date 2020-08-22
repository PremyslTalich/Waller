package eu.talich.waller.presentation.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import eu.talich.waller.presentation.common.model.CollectionVo
import eu.talich.waller.presentation.collections.vh.CollectionViewHolder
import eu.talich.waller.presentation.main.MainFragmentDirections
import eu.talich.waller.presentation.common.adapter.InfiniteLoader

class CollectionsAdapter(
    private var collections: MutableList<CollectionVo>,
    private val infiniteLoader: InfiniteLoader
): RecyclerView.Adapter<CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CollectionViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val collection = collections[position]

        holder.bind(collection)

        holder.itemView.setOnClickListener {
            val args = MainFragmentDirections.actionMainFragmentToCollectionDetailFragment(collection)
            it.findNavController().navigate(args)
        }

        if (position == itemCount - 1) {
            infiniteLoader.loadMore()
        }
    }

    override fun getItemCount(): Int = collections.size

    fun addCollections(newCollections: List<CollectionVo>) {
        val startIndex = itemCount
        collections.addAll(newCollections)
        notifyItemRangeInserted(startIndex, newCollections.size)
    }
}
