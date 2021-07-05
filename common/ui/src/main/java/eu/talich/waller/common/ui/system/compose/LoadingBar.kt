package eu.talich.waller.common.ui.system.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoadingBar() {
    MaterialTheme {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}