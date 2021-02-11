package eu.talich.waller.presentation.search

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import eu.talich.waller.presentation.search.ui.SearchCard
import eu.talich.waller.presentation.search.vm.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchDialogFragment : DialogFragment() {
    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val searchQuery = viewModel.searchQuery.value

                SearchCard(
                    searchQuery = searchQuery,
                    onSearchQueryChanged = viewModel::onSearchQueryChanged,
                    onClearSearchQueryClick = viewModel::clearSearchQuery
                )
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        viewModel.setNewSearchQuery()
    }
}