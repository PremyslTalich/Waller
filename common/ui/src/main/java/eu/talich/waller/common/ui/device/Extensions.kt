package eu.talich.waller.common.ui.device

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.View.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wolt.blurhashkt.BlurHashDecoder
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadPhoto(
    url: String,
    color: String? = null,
    blurHash: BlurHashVo? = null
) {
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
