package eu.talich.waller.presentation.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentPhotoDetailBinding
import eu.talich.waller.presentation.common.extension.loadPhoto
import eu.talich.waller.presentation.photodetail.vm.PhotoDetailViewModel
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
        val view = binding.root

        println("${viewModel.photo}")

        // TODO pořešit ten double bang!!
        binding.photo.loadPhoto(viewModel.photo.smallUrl!!)

        return view
    }
}