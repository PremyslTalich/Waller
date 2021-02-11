package eu.talich.waller.presentation.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import eu.talich.waller.R
import eu.talich.waller.databinding.FragmentMainBinding
import eu.talich.waller.presentation.main.adapter.MainPagerAdapter
import eu.talich.waller.presentation.main.vm.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent


class MainFragment : Fragment(R.layout.fragment_main), KoinComponent {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainPagerAdapter = MainPagerAdapter(requireActivity())

        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.pager.adapter = mainPagerAdapter
        mainPagerAdapter.fragments = viewModel.pagerFragments

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = viewModel.getTabTitle(position)
        }.attach()

        binding.pager.offscreenPageLimit = viewModel.pagerFragments.size - 1

        binding.searchButton.setOnClickListener {
            showSearchDialog()
        }

        return view
    }

    private fun showSearchDialog() {
        findNavController().navigate(R.id.action_mainFragment_to_searchDialogFragment)
    }
}
