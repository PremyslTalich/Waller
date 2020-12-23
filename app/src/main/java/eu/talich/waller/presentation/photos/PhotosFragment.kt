package eu.talich.waller.presentation.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentPhotosBinding
import eu.talich.waller.presentation.common.TabbedFragment
import eu.talich.waller.presentation.common.adapter.InfiniteLoader
import eu.talich.waller.presentation.photos.adapter.PhotosAdapter
import eu.talich.waller.presentation.photos.vm.PhotosViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment(R.layout.fragment_photos),
    InfiniteLoader, TabbedFragment {

    override val title: Int
        get() = R.string.photos_title

    private lateinit var binding: FragmentPhotosBinding
    private val viewModel by viewModel<PhotosViewModel>()

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

        return view
    }

    private fun observePhotos() {
        lifecycleScope.launch {
            viewModel.photos.collect { value ->
                photosAdapter.addPhotos(value)
            }
        }
    }

    override fun loadMore() {
        viewModel.loadMorePhotos()
    }
}