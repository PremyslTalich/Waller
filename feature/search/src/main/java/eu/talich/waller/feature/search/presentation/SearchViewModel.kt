package eu.talich.waller.feature.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.library.search.domain.GetSearchQueryUseCase
import eu.talich.waller.library.search.domain.SetSearchQueryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val setSearchQueryUseCase: SetSearchQueryUseCase,
    private val getSearchQueryUseCase: GetSearchQueryUseCase
) : ViewModel() {
    var searchQuery = ""

    fun getInitSearchQuery(): String {
        return getSearchQueryUseCase() ?: ""
    }

    fun onSearchQueryChanged(newSearchQuery: String) {
        searchQuery = newSearchQuery
    }

    fun setNewSearchQuery() {
        viewModelScope.launch(Dispatchers.IO) {
            setSearchQueryUseCase(
                if (searchQuery.isBlank()) {
                    null
                } else {
                    searchQuery
                }
            )
        }
    }
}
