package eu.talich.waller.presentation.photodetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import eu.talich.domain.model.User
import eu.talich.waller.R
import eu.talich.waller.presentation.common.extension.toPrettyString
import java.util.*

@Composable
fun PhotoDetailCard(
    user: User,
    description: String?,
    createdAt: Date,
    likes: Int,
    tags: List<String>
) {

    val defaultGray = Color(117,117,117)

    Column {
        Text(text = user.name, fontSize = TextUnit.Sp(20), color = Color.Black)
        Text(text = user.username, fontSize = TextUnit.Sp(12), fontStyle = FontStyle.Italic, color = defaultGray, modifier = Modifier.padding(start = 10.dp))
        if (!description.isNullOrBlank()) {
            Text(text = description, fontStyle = FontStyle.Italic, color = Color.Black, modifier = Modifier.padding(top = 24.dp))
        }
        Row(modifier = Modifier.padding(top = 24.dp)) {
            Image(vectorResource(id = R.drawable.ic_calendar))
            Text(text = createdAt.toPrettyString(), fontSize = TextUnit.Sp(14), color = defaultGray, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = likes.toString(), fontStyle = FontStyle.Italic)
            Image(vectorResource(id = R.drawable.ic_heart), modifier = Modifier.padding(start = 5.dp).scale(0.75f).align(Alignment.CenterVertically))
        }
        if (tags.isNotEmpty()) {
            Text(text = tags.joinToString(), fontSize = TextUnit.Sp(14), fontStyle = FontStyle.Italic, color = defaultGray, modifier = Modifier.padding(top = 24.dp))
        }
    }
}