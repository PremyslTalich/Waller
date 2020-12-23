package eu.talich.waller.presentation.main

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import eu.talich.waller.databinding.ActivityMainBinding
import eu.talich.waller.presentation.main.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class MainActivity: AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}