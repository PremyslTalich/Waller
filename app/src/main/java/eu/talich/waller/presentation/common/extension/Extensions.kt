package eu.talich.waller.presentation.common.extension

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import eu.talich.waller.R
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadPhoto(url: String, color: String? = null) {
    val placeholderColor = color?.let {
        ColorDrawable(Color.parseColor(color))
    }

    Glide.with(this.context)
        .load(url)
        .placeholder(placeholderColor)
        .into(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.toPrettyString() : String {
    val format = SimpleDateFormat("yyyy/MM/dd")
    return format.format(this)
}

fun AppCompatActivity.showToolbar() {
    this.findViewById<Toolbar>(R.id.search_toolbar)?.visibility = View.VISIBLE
}

fun AppCompatActivity.hideToolbar() {
    this.findViewById<Toolbar>(R.id.search_toolbar)?.visibility = View.GONE
}

fun AppCompatActivity.enterFullScreenMode() {
    this.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
}

fun AppCompatActivity.exitFullScreenMode() {
    this.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
}