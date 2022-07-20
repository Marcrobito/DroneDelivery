package dev.eighteentech.dronedelivery.entities

data class DroneTravel(
    val drone: String,
    val travels: Map<String, List<String>>
)
