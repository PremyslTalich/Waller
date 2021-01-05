package eu.talich.waller.presentation.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentCollectionsBinding
import eu.talich.waller.presentation.collections.adapter.CollectionsAdapter
import eu.talich.waller.presentation.collections.vm.BadConnection
import eu.talich.waller.presentation.collections.vm.CollectionsViewModel
import eu.talich.waller.presentation.collections.vm.EmptySearch
import eu.talich.waller.presentation.collections.vm.NoInternet
import eu.talich.waller.presentation.common.TabbedFragment
import eu.talich.waller.presentation.common.adapter.ClearAdapter
import eu.talich.waller.presentation.common.adapter.InfiniteLoader
import eu.talich.waller.presentation.common.ui.BackgroundAlert
import eu.talich.waller.presentation.common.ui.LoadingBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CollectionsFragment : Fragment(R.layout.fragment_collections), InfiniteLoader, TabbedFragment,
    ClearAdapter {

    override val title: Int
        get() = R.string.collections_title

    private lateinit var binding: FragmentCollectionsBinding
    private val viewModel: CollectionsViewModel by viewModel { parametersOf(this) }

    private val collectionsAdapter = CollectionsAdapter(mutableListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.collections.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = collectionsAdapter
        }

        binding.noCollectionsAlert.setContent {
            val state by viewModel.state.collectAsState()

            MaterialTheme {
                when(state) {
                    is EmptySearch -> BackgroundAlert(R.drawable.ic_search_off, R.string.no_collections_found)
                    is BadConnection -> BackgroundAlert(R.drawable.ic_bad_connection, R.string.bad_unsplash_connection)
                    is NoInternet -> BackgroundAlert(R.drawable.ic_no_internet, R.string.no_internet)
                    else -> Unit
                }
            }
        }

        binding.loadingBar.setContent {
            val state by viewModel.loadingBarState.collectAsState()

            if (state) {
                LoadingBar()
            }
        }

        observeCollections()

        return view
    }

    private fun observeCollections() {
        lifecycleScope.launch {
            viewModel.collections.collect { value ->
                collectionsAdapter.addCollections(value)
            }
        }
    }

    override fun loadMore() {
        viewModel.loadMoreCollections()
    }

    override fun clearAdapter() {
        requireActivity().runOnUiThread {
            collectionsAdapter.removeCollections()
        }
    }
}
