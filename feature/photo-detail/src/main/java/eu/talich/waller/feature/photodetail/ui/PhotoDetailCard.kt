package eu.talich.waller.presentation.photodetail.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import eu.talich.waller.common.photo.model.PhotoLocation
import eu.talich.waller.common.user.model.User
import eu.talich.waller.R
import eu.talich.waller.presentation.common.extension.toPrettyString
import eu.talich.waller.presentation.common.ui.Tag
import eu.talich.waller.presentation.common.ui.TagRow
import eu.talich.waller.presentation.common.ui.defaultGray
import java.util.*

@ExperimentalLayout
@Composable
fun PhotoDetailCard(
    user: eu.talich.waller.common.user.model.User,
    description: String?,
    createdAt: Date,
    likes: Int,
    location: eu.talich.waller.common.photo.model.PhotoLocation,
    tags: List<String>,
    onLocationClick: (location: eu.talich.waller.common.photo.model.PhotoLocation) -> Unit,
    onTagClick: (id: String) -> Unit
) {
    val context = AmbientContext.current

    Column {
        Text(text = user.name, fontSize = TextUnit.Sp(20), color = Color.Black)
        Text(text = user.username, fontSize = TextUnit.Sp(12), fontStyle = FontStyle.Italic, color = defaultGray, modifier = Modifier.padding(start = 10.dp))

        if (!description.isNullOrBlank()) {
            Text(text = description, fontStyle = FontStyle.Italic, color = Color.Black, modifier = Modifier.padding(top = 24.dp))
        }

        Row(modifier = Modifier.padding(top = 24.dp)) {
            Image(
                imageVector = vectorResource(id = R.drawable.ic_calendar),
                contentDescription = null
            )
            Text(text = createdAt.toPrettyString(), fontSize = TextUnit.Sp(14), color = defaultGray, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = likes.toString(), fontStyle = FontStyle.Italic)
            Image(
                imageVector = vectorResource(id = R.drawable.ic_heart),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .scale(0.75f)
                    .align(Alignment.CenterVertically)
            )
        }

        PhotoDetailCardLocation(context, location, onLocationClick)

        if (tags.isNotEmpty()) {
            ScrollableColumn(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .heightIn(max = 60.dp)
            ) {
                TagRow(tags = tags.map { Tag(it, it) }, onTagClick)
            }
        }
    }
}

@Composable
fun PhotoDetailCardLocation(context: Context, location: eu.talich.waller.common.photo.model.PhotoLocation, onLocationClick: (location: eu.talich.waller.common.photo.model.PhotoLocation) -> Unit) {
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
                imageVector = vectorResource(id = R.drawable.ic_globe),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .padding(start = 4.dp, top = 3.dp)
            )

            Column {
                (address ?: gpsLocation)?.let {
                    Text(text = it, modifier = Modifier.padding(start = 3.dp), fontSize = TextUnit.Sp(14), color = defaultGray, fontStyle = FontStyle.Italic)
                }

                if (address != null && gpsLocation != null) {
                    Text(text = "($gpsLocation)", modifier = Modifier.padding(start = 11.dp), fontSize = TextUnit.Sp(12), color = defaultGray, fontStyle = FontStyle.Italic)
                }
            }
        }
    }
}