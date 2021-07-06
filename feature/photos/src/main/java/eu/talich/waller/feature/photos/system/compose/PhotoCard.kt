package eu.talich.waller.feature.photos.system.compose

import android.graphics.Bitmap
import android.graphics.Color as AndroidColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.scale
import com.wolt.blurhashkt.BlurHashDecoder
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import eu.talich.waller.feature.photos.presentation.BlurHashVo
import eu.talich.waller.feature.photos.presentation.PhotoVo

@Composable
fun PhotoCard(photo: PhotoVo, onClick: (photo: PhotoVo) -> Unit) {
    val defaultGray = Color(55,55,55)

    MaterialTheme() {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 10.dp,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .clickable(onClick = {
                        onClick(photo)
                    })
            ) {
                val painter = rememberCoilPainter(photo.thumbnail.url)

                Box {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )

                    if (painter.loadState is ImageLoadState.Loading) {
                        val bm = getPlaceholderBitmap(
                            photo.thumbnail.width,
                            photo.thumbnail.height,
//                            photo.blurHash,
                            null,
                            AndroidColor.parseColor(photo.color)
                        )

                        Image(
                            bitmap = bm.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                }

                if (!photo.description.isNullOrBlank()) {
                    Text(
                        text = photo.description,
                        fontSize = 14.sp,
                        letterSpacing = 0.4.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Light,
                        color = defaultGray,
                        modifier = Modifier
                            .padding(
                                start = 7.dp,
                                end = 7.dp,
                                top = 4.dp,
                                bottom = 4.dp
                            )
                    )
                }
            }
        }
    }
}

private fun getPlaceholderBitmap(width: Int, height: Int, blurHash: BlurHashVo?, color: Int): Bitmap {
    return blurHash?.let {
        BlurHashDecoder.decode(it.hash, it.width, it.height)?.scale(width, height)
    } ?: run {
        Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).applyCanvas {
            drawColor(color)
        }
    }
}