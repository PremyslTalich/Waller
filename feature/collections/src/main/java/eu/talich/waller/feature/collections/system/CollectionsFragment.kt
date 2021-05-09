package eu.talich.waller.feature.collections.system

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
import eu.talich.waller.feature.collections.presentation.CollectionsAdapter
import eu.talich.waller.feature.collections.presentation.BadConnection
import eu.talich.waller.feature.collections.presentation.CollectionsViewModel
import eu.talich.waller.feature.collections.presentation.EmptySearch
import eu.talich.waller.feature.collections.presentation.NoInternet
import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.common.ui.system.compose.AlertRibbon
import eu.talich.waller.common.ui.system.compose.BackgroundAlert
import eu.talich.waller.common.ui.system.compose.LoadingBar
import eu.talich.waller.feature.collections.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

class CollectionsFragment : Fragment(R.layout.fragment_collections), MainScreenPage {

    override val title: Int
        get() = R.string.collections_title

    override val weight: Int
        get() = 20

    private lateinit var binding: FragmentCollectionsBinding
    private val viewModel: CollectionsViewModel by viewModel {
        parametersOf({
            requireActivity().runOnUiThread {
                collectionsAdapter.removeCollections()
            }
        })
    }

    private val collectionsAdapter = CollectionsAdapter(
        mutableListOf(),
        viewModel::loadMoreCollections
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.collections.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = collectionsAdapter
        }

        binding.noCollectionsAlert.setContent {
            val state by viewModel.alertState.collectAsState()

            if (state == EmptySearch) {
                MaterialTheme {
                    BackgroundAlert(R.drawable.ic_search_off, R.string.no_collections_found)
                }
            }
        }

        binding.alertRibbon.setContent {
            val state by viewModel.alertState.collectAsState()

            MaterialTheme {
                when(state) {
                    is BadConnection -> AlertRibbon(getString(R.string.bad_unsplash_connection))
                    is NoInternet -> AlertRibbon(getString(R.string.no_internet))
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
}
