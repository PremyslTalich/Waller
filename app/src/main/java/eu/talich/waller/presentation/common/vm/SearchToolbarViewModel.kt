package eu.talich.waller.presentation.common.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class SearchToolbarViewModel(): ViewModel() {
    private val _searchedQuery = MutableStateFlow<String?>(null)
    val searchedQuery: StateFlow<String?> = _searchedQuery

    fun setSearchedQuery(query: String?) {
        if (query.isNullOrBlank()) {
            _searchedQuery.value = null
        } else {
            _searchedQuery.value = query
        }
    }
}