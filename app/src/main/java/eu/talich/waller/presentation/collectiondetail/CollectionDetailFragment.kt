package eu.talich.waller.presentation.collectiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentCollectionDetailBinding
import eu.talich.waller.presentation.collectiondetail.adapter.PhotosAdapter
import eu.talich.waller.presentation.collectiondetail.vm.CollectionDetailViewModel
import eu.talich.waller.presentation.collectiondetail.vm.None
import eu.talich.waller.presentation.collectiondetail.vm.BadConnection
import eu.talich.waller.presentation.collectiondetail.vm.NoInternet
import eu.talich.waller.presentation.common.adapter.InfiniteLoader
import eu.talich.waller.presentation.common.extension.loadPhoto
import eu.talich.waller.presentation.common.ui.LoadingBar
import eu.talich.waller.presentation.common.ui.AlertRibbon
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CollectionDetailFragment : Fragment(R.layout.fragment_collection_detail), InfiniteLoader, MotionLayout.TransitionListener {

    private lateinit var binding: FragmentCollectionDetailBinding
    private val args: CollectionDetailFragmentArgs by navArgs()
    private val viewModel: CollectionDetailViewModel by viewModel { parametersOf(args.collection) }

    private val photosAdapter = PhotosAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeCollectionPhotos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        if (viewModel.collection.description.isNotBlank()) {
            binding.description.text = viewModel.collection.description
        } else {
            binding.description.text = viewModel.collection.title
        }

        viewModel.collection.coverPhoto?.smallUrl?.let {
            binding.coverPhoto.loadPhoto(it)
        }

        binding.photos.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = photosAdapter
        }

        binding.coverPhoto.setOnClickListener {
            viewModel.collection.coverPhoto?.let { photo ->
                val args = CollectionDetailFragmentDirections.actionCollectionDetailFragmentToPhotoDetailFragment(photo)
                it.findNavController().navigate(args)
            }
        }

        binding.loadingBar.setContent {
            val loadingState by viewModel.loadingBarState.collectAsState()

            if (loadingState) {
                LoadingBar()
            }
        }

        binding.alertRibbon.setContent {
            val alertState by viewModel.alertState.collectAsState()

            when (alertState) {
                None -> Unit
                BadConnection -> AlertRibbon(getString(R.string.bad_unsplash_connection))
                NoInternet -> AlertRibbon(getString(R.string.no_internet))
            }
        }

        binding.motionLayout.setTransitionListener(this)

        return view
    }

    override fun onResume() {
        super.onResume()

        if (viewModel.hasTransitionEnded) {
            binding.motionLayout.progress = 1.0f
        }
    }

    private fun observeCollectionPhotos() {
        lifecycleScope.launch {
            viewModel.photos.collect { value ->
                photosAdapter.addPhotos(value)
            }
        }
    }

    override fun loadMore() {
        viewModel.loadMoreCollectionPhotos()
    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
        viewModel.hasTransitionEnded = (p1 == R.id.end)
    }
}
