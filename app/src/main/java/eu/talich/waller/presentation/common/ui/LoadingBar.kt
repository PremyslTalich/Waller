package eu.talich.waller.presentation.common.ui

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoadingBar() {
    MaterialTheme {
        LinearProgressIndicator()
    }
}