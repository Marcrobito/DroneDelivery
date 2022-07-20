package dev.eighteentech.dronedelivery

import android.net.Uri

interface MainRepository {

    fun getDronesTravelList(uri: Uri): List<Map<String, List<String>>>
}
