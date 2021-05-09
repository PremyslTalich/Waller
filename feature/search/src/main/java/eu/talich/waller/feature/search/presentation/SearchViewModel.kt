package eu.talich.waller.feature.search.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.library.search.domain.ObserveSearchQueryUseCase
import eu.talich.waller.library.search.domain.SetSearchQueryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val setSearchQueryUseCase: SetSearchQueryUseCase,
    private val observeSearchQueryUseCase: ObserveSearchQueryUseCase
) : ViewModel() {
    val searchQuery: MutableState<String> = mutableStateOf("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            observeSearchQueryUseCase().collect {
                withContext(Dispatchers.Main) {
                    onSearchQueryChanged(it ?: "")
                }
            }
        }
    }

    fun onSearchQueryChanged(newSearchQuery: String) {
        searchQuery.value = newSearchQuery
    }

    fun clearSearchQuery() {
        searchQuery.value = ""
    }

    fun setNewSearchQuery() {
        viewModelScope.launch(Dispatchers.IO) {
            setSearchQueryUseCase(
                if (searchQuery.value.isBlank()) {
                    null
                } else {
                    searchQuery.value
                }
            )
        }
    }
}
