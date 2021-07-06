package eu.talich.waller.feature.search.system.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.talich.waller.feature.search.R


@Composable
fun SearchCard(
    initSearchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    val textState = remember {
        mutableStateOf(
            TextFieldValue(initSearchQuery)
        )
    }

    MaterialTheme {
        Card() {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.search_dialog_title),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )

                TextField(
                    value = textState.value,
                    onValueChange = {
                        textState.value = it
                        onSearchQueryChanged(it.text)
                    },
                    trailingIcon = {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_clear),
                            contentDescription = null,
                            modifier = Modifier
                                .background(
                                    Color.Transparent,
                                    CircleShape
                                )
                                .clip(CircleShape)
                                .size(36.dp)
                                .clickable {
                                    textState.value = TextFieldValue("")
                                }
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .background(Color.Transparent)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchCardPreview() {
    SearchCard(
        initSearchQuery = "Mountains",
        onSearchQueryChanged = {}
    )
}