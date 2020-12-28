package eu.talich.waller.presentation.main.vm

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import eu.talich.domain.usecase.GetSearchQueryUseCase
import eu.talich.domain.usecase.SetSearchQueryUseCase
import eu.talich.waller.presentation.common.TabbedFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MainFragmentViewModel(
    val app: Application,
    val pagerFragments: List<Fragment>,
    private val setSearchQueryUseCase: SetSearchQueryUseCase,
    private val getSearchQueryUseCase: GetSearchQueryUseCase,
): AndroidViewModel(app), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    var searchQuery: String? = null

    init {
        launch {
            getSearchQueryUseCase().collect {
                searchQuery = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getTabTitle(position: Int): String {
        return app.getString((pagerFragments[position] as TabbedFragment).title)
    }

    fun setNewSearchQuery(newSearchQuery: String) {
        launch {
            setSearchQueryUseCase(
                if (newSearchQuery.isBlank()) {
                    null
                } else {
                    newSearchQuery
                }
            )
        }
    }
}
