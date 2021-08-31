package eu.talich.waller.common.ui.system.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun TagRow(
    tags: List<Tag>,
    onClick: (id: String) -> Unit
) {
    MaterialTheme {
        FlowRow(
            mainAxisSpacing = 5.dp,
            crossAxisSpacing = 3.dp
        ) {
            tags.forEach {
                Text(
                    text = it.text,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .background(
                            Color(0,0,0, 7),
                            RoundedCornerShape(10.dp)
                        )
                        .border(
                            1.dp,
                            Color(0,0,0, 20),
                            RoundedCornerShape(10.dp)
                        )
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                        .clickable(onClick = {
                            onClick(it.id)
                        })
                        .padding(
                            top = 0.dp,
                            bottom = 3.dp,
                            start = 5.dp,
                            end = 5.dp
                        )
                )
            }
        }
    }
}

data class Tag(
    val text: String,
    val id: String
)