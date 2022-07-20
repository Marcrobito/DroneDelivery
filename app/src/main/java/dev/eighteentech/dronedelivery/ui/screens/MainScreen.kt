package dev.eighteentech.dronedelivery.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.eighteentech.dronedelivery.MainViewModel
import dev.eighteentech.dronedelivery.ui.components.DroneButton
import dev.eighteentech.dronedelivery.ui.components.DroneTravelRow
import dev.eighteentech.dronedelivery.ui.theme.DroneDeliveryTheme

@Composable
fun MainScreen(mainViewModel: MainViewModel, onClick: () -> Unit = {}) {
    val travels: List<Map<String, List<String>>> by mainViewModel.droneTravels.observeAsState(
        emptyList()
    )
    DroneDeliveryTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DroneButton(
                        onClick = { onClick() },
                        text = "Select a file"
                    )
                }
                travels.forEachIndexed { index, map ->
                    Column() {
                        Text(
                            text = "Travel ${index + 1}",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .border(width = 1.dp, color = Color.Black)
                        )
                        map.forEach {
                            if (it.value.isNotEmpty())
                                DroneTravelRow(droneTravel = it)
                        }
                    }
                }
            }
        }
    }
}
