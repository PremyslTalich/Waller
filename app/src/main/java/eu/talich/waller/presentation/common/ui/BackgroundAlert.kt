package eu.talich.waller.presentation.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.talich.waller.R

@Preview
@Composable
fun BackgroundAlert(
    vectorImageResId: Int = R.drawable.ic_search_off,
    messageResId: Int = R.string.no_photos_found
) {
    val context = AmbientContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            vectorResource(vectorImageResId),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(86.dp)
                .wrapContentWidth()
        )
        Text(
            text = context.getString(messageResId),
            modifier = Modifier.padding(top = 24.dp),
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            color = Color(117,117,117)
        )
    }
}
