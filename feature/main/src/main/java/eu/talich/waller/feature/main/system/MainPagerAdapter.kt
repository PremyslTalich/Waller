package eu.talich.waller.feature.main.system

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import eu.talich.waller.common.ui.system.MainScreenPage

class MainPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    var fragments: List<MainScreenPage> = emptyList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position] as Fragment
    }
}