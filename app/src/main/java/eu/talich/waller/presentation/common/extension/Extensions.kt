package eu.talich.waller.presentation.common.extension

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadPhoto(url: String, color: String? = null) {
    val placeholderColor = color?.let {
        ColorDrawable(Color.parseColor(color))
    }

    Glide.with(this.context)
        .load(url)
        .placeholder(placeholderColor)
        .into(this)
}