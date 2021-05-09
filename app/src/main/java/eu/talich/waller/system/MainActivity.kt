package eu.talich.waller.system

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import eu.talich.waller.R
import eu.talich.waller.databinding.ActivityMainBinding
import eu.talich.waller.feature.main.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity: AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<eu.talich.waller.feature.main.vm.MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Remove splash theme
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}
