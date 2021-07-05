package eu.talich.waller.system

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import eu.talich.waller.R
import eu.talich.waller.databinding.ActivityMainBinding
import eu.talich.waller.device.WallerRouter
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val wallerRouter: WallerRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Remove splash theme
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wallerRouter.setNavController(
            findNavController(this, R.id.nav_host)
        )
    }
}
