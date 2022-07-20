package dev.eighteentech.dronedelivery.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DroneTravelRow(droneTravel: Map.Entry<String, List<String>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = droneTravel.key,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            droneTravel.value.forEach { item ->
                Column() {
                    Text(text = item)
                }
            }
        }
    }
}

private val travelDummy2 = mapOf("DroneB" to listOf("LocationB", "LocationC", "LocationG"))

@Preview(showBackground = true)
@Composable
fun DroneTravelRowPreview() {
    DroneTravelRow(droneTravel = travelDummy2.entries.first())
}
