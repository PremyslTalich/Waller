package eu.talich.waller.feature.main.system

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import eu.talich.waller.feature.main.R
import eu.talich.waller.feature.main.databinding.FragmentMainBinding
import eu.talich.waller.feature.main.presentation.MainFragmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mainPagerAdapter = MainPagerAdapter(requireActivity())

        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.pager.adapter = mainPagerAdapter
        mainPagerAdapter.fragments = viewModel.pagerFragments

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = viewModel.getTabTitle(position)
        }.attach()

        binding.pager.offscreenPageLimit = viewModel.pagerFragments.size

        binding.searchButton.setOnClickListener {
            viewModel.navigateToSearchDialog()
        }

        return view
    }
}
