package eu.talich.waller.presentation.collectiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentCollectionDetailBinding
import eu.talich.waller.presentation.collectiondetail.adapter.PhotosAdapter
import eu.talich.waller.presentation.collectiondetail.vm.CollectionDetailViewModel
import eu.talich.waller.presentation.common.adapter.InfiniteLoader
import eu.talich.waller.presentation.common.extension.loadPhoto
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CollectionDetailFragment : Fragment(R.layout.fragment_collection_detail), InfiniteLoader {

    private lateinit var binding: FragmentCollectionDetailBinding
    private val args: CollectionDetailFragmentArgs by navArgs()
    private val viewModel: CollectionDetailViewModel by viewModel { parametersOf(args.collection) }

    private val photosAdapter = PhotosAdapter(mutableListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.title.text = viewModel.collection.title
        binding.description.text = viewModel.collection.description

        viewModel.collection.coverPhotoUrl?.let {
            binding.coverPhoto.loadPhoto(it)
        }

//        binding.photos.apply {
//            layoutManager = LinearLayoutManager(view.context)
//            adapter = photosAdapter
//        }

        observeCollectionPhotos()

        return view
    }

    private fun observeCollectionPhotos() {
        lifecycleScope.launch {
//            viewModel.photos.collect { value ->
//                photosAdapter.addPhotos(value)
//            }
        }
    }

    override fun loadMore() {
        viewModel.loadMoreCollectionPhotos()
    }
}