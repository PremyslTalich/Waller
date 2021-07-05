package eu.talich.waller.feature.photodetail.system.compose

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.talich.waller.common.ui.system.compose.Tag
import eu.talich.waller.common.ui.system.compose.TagRow
import eu.talich.waller.common.ui.system.compose.defaultGray
import eu.talich.waller.common.ui.system.toPrettyString
import eu.talich.waller.feature.photodetail.presentation.PhotoDetailVo
import eu.talich.waller.feature.photodetail.R

@Composable
fun PhotoDetailCard(
    photo: PhotoDetailVo,
    onLocationClick: (location: PhotoDetailVo.LocationVo) -> Unit,
    onTagClick: (id: String) -> Unit
) {
    val context = LocalContext.current

    Column {
        Text(text = photo.user.name, fontSize = 20.sp, color = Color.Black)
        Text(text = photo.user.username, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = defaultGray, modifier = Modifier.padding(start = 10.dp))

        if (!photo.description.isNullOrBlank()) {
            Text(text = photo.description, fontStyle = FontStyle.Italic, color = Color.Black, modifier = Modifier.padding(top = 24.dp))
        }

        Row(modifier = Modifier.padding(top = 24.dp)) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                contentDescription = null
            )
            Text(text = photo.createdAt.toPrettyString(), fontSize = 14.sp, color = defaultGray, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = photo.likes.toString(), fontStyle = FontStyle.Italic)
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .scale(0.75f)
                    .align(Alignment.CenterVertically)
            )
        }

        PhotoDetailCardLocation(context, photo.location, onLocationClick)

//        if (photo.tags.isNotEmpty()) {
//            ScrollableColumn(
//                modifier = Modifier
//                    .padding(top = 10.dp)
//                    .heightIn(max = 60.dp)
//            ) {
//                TagRow(tags = photo.tags.map { Tag(it, it) }, onTagClick)
//            }
//        }
    }
}

@Composable
fun PhotoDetailCardLocation(context: Context, location: PhotoDetailVo.LocationVo, onLocationClick: (location: PhotoDetailVo.LocationVo) -> Unit) {
    val address = with(location) {
        city?.let { city ->
            country?.let { country ->
                context.getString(R.string.photo_location_both, city, country)
            } ?: run {
                city
            }
        } ?: run {
            country
        }
    }

    val gpsLocation = with(location) {
        if (latitude != null && longitude != null) {
            "$latitude, $longitude"
        } else {
            null
        }
    }

    if (address != null || gpsLocation != null) {
        Row(modifier = Modifier.clickable(onClick = { onLocationClick(location) }).fillMaxWidth()) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_globe),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .padding(start = 4.dp, top = 3.dp)
            )

            Column {
                (address ?: gpsLocation)?.let {
                    Text(text = it, modifier = Modifier.padding(start = 3.dp), fontSize = 14.sp, color = defaultGray, fontStyle = FontStyle.Italic)
                }

                if (address != null && gpsLocation != null) {
                    Text(text = "($gpsLocation)", modifier = Modifier.padding(start = 11.dp), fontSize = 12.sp, color = defaultGray, fontStyle = FontStyle.Italic)
                }
            }
        }
    }
}