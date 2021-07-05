package eu.talich.waller.feature.main.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.talich.waller.common.navigation.model.Search
import eu.talich.waller.common.ui.system.MainScreenPage
import eu.talich.waller.library.navigation.domain.NavigateUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainFragmentViewModel(
    private val app: Application,
    val pagerFragments: List<MainScreenPage>,
    private val navigateUseCase: NavigateUseCase
): AndroidViewModel(app) {
    fun getTabTitle(position: Int): String {
        return app.getString(pagerFragments[position].title)
    }

    fun navigateToSearchDialog() {
        viewModelScope.launch {
            navigateUseCase(Search)
        }
    }
}
