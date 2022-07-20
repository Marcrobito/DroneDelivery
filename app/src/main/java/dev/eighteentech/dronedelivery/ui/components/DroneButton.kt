package dev.eighteentech.dronedelivery.ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DroneButton(
    text: String? = null,
    onClick: () -> Unit = {}
) {
    Button(onClick = onClick) {
        text?.let {
            Text(it)
        }
    }
}
