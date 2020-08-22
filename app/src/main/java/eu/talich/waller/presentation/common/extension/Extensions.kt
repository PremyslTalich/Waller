package eu.talich.waller.presentation.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import eu.talich.waller.R

fun ImageView.loadPhoto(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}