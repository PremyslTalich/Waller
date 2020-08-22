package eu.talich.waller.presentation.main.vm

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import eu.talich.waller.presentation.common.TabbedFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MainFragmentViewModel(
    val app: Application,
    val pagerFragments: List<Fragment>
): AndroidViewModel(app), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getTabTitle(position: Int): String {
        return app.getString((pagerFragments[position] as TabbedFragment).title)
    }
}