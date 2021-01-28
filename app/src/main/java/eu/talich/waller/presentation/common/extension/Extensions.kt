package eu.talich.waller.presentation.common.extension

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.View.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wolt.blurhashkt.BlurHashDecoder
import eu.talich.waller.presentation.common.model.BlurHashVo
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadPhoto(url: String, color: String? = null, blurHash: BlurHashVo? = null) {
    val placeholderColor =
        blurHash?.let {
            BitmapDrawable(
                this.resources,
                BlurHashDecoder.decode(it.hash, it.width, it.height)
            )
        } ?: run {
            color?.let {
                ColorDrawable(Color.parseColor(color))
            }
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

fun AppCompatActivity.enterFullScreenMode() {
    window.decorView.systemUiVisibility = (
        SYSTEM_UI_FLAG_HIDE_NAVIGATION
        or SYSTEM_UI_FLAG_FULLSCREEN
    )
}

fun AppCompatActivity.exitFullScreenMode() {
    window.decorView.systemUiVisibility = (
        SYSTEM_UI_FLAG_LAYOUT_STABLE
    )
}