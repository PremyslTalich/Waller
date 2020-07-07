package eu.talich.waller.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.talich.waller.databinding.ActivityMainBinding
import eu.talich.waller.presentation.main.adapter.ImagesAdapter
import eu.talich.waller.presentation.main.adapter.InfiniteLoader
import eu.talich.waller.presentation.main.vm.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), InfiniteLoader {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    private val imagesAdapter = ImagesAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.images.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = imagesAdapter
        }

        observeImages()
    }

    private fun observeImages() {
        lifecycleScope.launch {
            viewModel.images.collect { value ->
                Log.i("tady", value.toString())

                imagesAdapter.addImages(value)
            }
        }
    }

    override fun loadMore() {
        viewModel.loadMoreImages()
    }
}