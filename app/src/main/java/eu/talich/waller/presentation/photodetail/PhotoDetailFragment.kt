package eu.talich.waller.presentation.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentPhotoDetailBinding
import eu.talich.waller.presentation.common.extension.enterFullScreenMode
import eu.talich.waller.presentation.common.extension.exitFullScreenMode
import eu.talich.waller.presentation.common.extension.loadPhoto
import eu.talich.waller.presentation.common.extension.toPrettyString
import eu.talich.waller.presentation.photodetail.ui.PhotoDetailCard
import eu.talich.waller.presentation.photodetail.vm.PhotoDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PhotoDetailFragment : Fragment(R.layout.fragment_photo_detail) {

    private lateinit var binding: FragmentPhotoDetailBinding
    private val args: PhotoDetailFragmentArgs by navArgs()
    private val viewModel: PhotoDetailViewModel by viewModel { parametersOf(args.photo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).enterFullScreenMode()

        val view = binding.root

        // TODO pořešit ten double bang!!
        binding.photo.loadPhoto(viewModel.photo.fullUrl!!)

        observePhotoDetail()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as AppCompatActivity).exitFullScreenMode()
    }

    private fun observePhotoDetail() {
        lifecycleScope.launch {
            viewModel.photoDetail.collect { photoDetail ->
                photoDetail?.let {
                    binding.photoDetailCard.setContent {
                        MaterialTheme {
                            with(it) {
                                PhotoDetailCard(user, description, createdAt, likes, tags)
                            }
                        }
                    }
                }
            }
        }
    }
}