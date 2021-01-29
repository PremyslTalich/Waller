package eu.talich.waller.presentation.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.koin.core.component.KoinComponent

class MainPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity), KoinComponent {
    var fragments: List<Fragment> = emptyList()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}