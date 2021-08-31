package eu.talich.waller.feature.collections.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.common.ui.system.compose.AlertRibbon
import eu.talich.waller.common.ui.system.compose.BackgroundAlert
import eu.talich.waller.common.ui.system.compose.LoadingBar
import eu.talich.waller.feature.collections.R
import eu.talich.waller.feature.collections.databinding.FragmentCollectionsBinding
import eu.talich.waller.feature.collections.presentation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.parameter.parametersOf

class CollectionsFragment : Fragment(R.layout.fragment_collections), MainScreenPage {

    override val title: Int
        get() = R.string.collections_title

    override val weight: Int
        get() = 20

    private lateinit var binding: FragmentCollectionsBinding
    private val viewModel by viewModel<CollectionsViewModel>()

    private lateinit var collectionsAdapter: CollectionsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)
        val view = binding.root

        collectionsAdapter = CollectionsAdapter(
            mutableListOf(),
            viewModel::loadMoreCollections,
            viewModel::navigateToCollectionDetail
        )

        binding.collections.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = collectionsAdapter
        }

        observeViewState()

        return view
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                withContext(Dispatchers.Main) {
                    collectionsAdapter.setCollections(state.collections)

                    binding.noCollectionsAlert.setContent {
                        if (state.alert == CollectionsViewState.AlertState.EMPTY_SEARCH) {
                            MaterialTheme {
                                BackgroundAlert(R.drawable.ic_search_off, R.string.no_collections_found)
                            }
                        }
                    }

                    binding.alertRibbon.setContent {
                        MaterialTheme {
                            when(state.alert) {
                                CollectionsViewState.AlertState.BAD_CONNECTION -> AlertRibbon(getString(R.string.bad_unsplash_connection))
                                CollectionsViewState.AlertState.NO_INTERNET -> AlertRibbon(getString(R.string.no_internet))
                            }
                        }
                    }

                    binding.loadingBar.setContent {
                        if (state.loading) {
                            LoadingBar()
                        }
                    }
                }
            }
        }
    }
}
