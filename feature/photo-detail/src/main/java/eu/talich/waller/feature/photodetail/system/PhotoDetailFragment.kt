package eu.talich.waller.feature.photodetail.system

import android.net.Uri
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import eu.talich.waller.feature.photodetail.presentation.PhotoDetailViewModel
import eu.talich.waller.common.ui.system.enterFullScreenMode
import eu.talich.waller.common.ui.system.exitFullScreenMode
import eu.talich.waller.common.ui.system.loadPhoto
import eu.talich.waller.feature.photodetail.R
import eu.talich.waller.feature.photodetail.databinding.FragmentPhotoDetailBinding
import eu.talich.waller.feature.photodetail.presentation.PhotoDetailVo
import eu.talich.waller.feature.photodetail.system.compose.PhotoDetailCard
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class PhotoDetailFragment : Fragment(R.layout.fragment_photo_detail) {

    private lateinit var binding: FragmentPhotoDetailBinding
    private val args: PhotoDetailFragmentArgs by navArgs()
    private val viewModel: PhotoDetailViewModel by viewModel { parametersOf(args.photo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).enterFullScreenMode()

        val view = binding.root

        binding.photo.loadPhoto(
            url = viewModel.photo.url,
            color = viewModel.photo.color,
            blurHash = viewModel.photo.blurHash?.hash,
            blurHashWidth = viewModel.photo.blurHash?.width,
            blurHashHeight = viewModel.photo.blurHash?.height
        )
        binding.photo.maximumScale = 5f

        observePhotoDetail()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as AppCompatActivity).exitFullScreenMode()
    }

    // TODO: move to the viewModel
    private fun onLocationClick(location: PhotoDetailVo.LocationVo) {
        fun getUri(): String {
            val hasGpsLocation = (location.latitude != null && location.longitude != null)
            val hasCityAndCountry = (location.city != null && location.country != null)

            return if (hasGpsLocation) {
                "geo:${location.latitude},${location.longitude}?q=${location.latitude},${location.longitude}"
            } else {
                if (hasCityAndCountry) {
                    "geo:0,0?q=${location.city}, ${location.country}"
                } else {
                    "geo:0,0?q=${location.city ?: location.country}"
                }
            }
        }

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(getUri())
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun observePhotoDetail() {
        lifecycleScope.launch {
            viewModel.photoDetail.collect { photoDetail ->
                photoDetail?.let {
                    binding.photoDetailCard.setContent {
                        MaterialTheme {
                            PhotoDetailCard(
                                it,
                                ::onLocationClick,
                                viewModel::onTagClicked
                            )
                        }
                    }
                }
            }
        }
    }
}
