package eu.talich.waller.presentation.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentPhotosBinding
import eu.talich.waller.presentation.common.TabbedFragment
import eu.talich.waller.presentation.common.adapter.ClearAdapter
import eu.talich.waller.presentation.common.adapter.InfiniteLoader
import eu.talich.waller.presentation.common.ui.BackgroundAlert
import eu.talich.waller.presentation.photos.adapter.PhotosAdapter
import eu.talich.waller.presentation.photos.vm.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PhotosFragment : Fragment(R.layout.fragment_photos),
    InfiniteLoader, TabbedFragment, ClearAdapter {

    override val title: Int
        get() = R.string.photos_title

    private lateinit var binding: FragmentPhotosBinding
    private val viewModel: PhotosViewModel by viewModel { parametersOf(this) }

    private val photosAdapter = PhotosAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observePhotos()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.images.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = photosAdapter
        }

        binding.noImagesAlert.setContent {
            MaterialTheme {
                BackgroundAlert(R.drawable.ic_search_off, R.string.no_photos_found)
            }
        }

        observeState()

        return view
    }

    private fun observePhotos() {
        lifecycleScope.launch {
            viewModel.photos.collect { value ->
                photosAdapter.addPhotos(value)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect { value ->
                when (value) {
                    is EmptySearch -> {
                        // add observable for the EmptyListAlert and change the value here
                        // (so i can change the message and image in EmptyListAlert on fly)
                        binding.noImagesAlert.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.noImagesAlert.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun loadMore() {
        viewModel.loadMorePhotos()
    }

    override fun clearAdapter() {
        requireActivity().runOnUiThread {
            photosAdapter.removePhotos()
        }
    }
}
