package dev.eighteentech.dronedelivery

import android.app.Application
import android.net.Uri
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val app: Application) : MainRepository {

    override fun getDronesTravelList(uri: Uri): List<Map<String, List<String>>> {
        getBytesFromUri(uri)?.let { byteArray ->
            val result = String(byteArray).replace("[", "")
                .replace(",", "").replace(" ", "")
                .dropLast(1).split("]")
            val map = mutableMapOf<String, Int>()
            for (i in result.indices step 2) {
                map[result[i]] = result[i + 1].toInt()
            }
            val drones = map.filter { it.key.indexOf("Drone") != -1 }.toMap()
            val locations = map.filter { it.key.indexOf("Location") != -1 }.toMap()

            return getDronesTravels(drones, locations.toMutableMap())
        }
        return emptyList()
    }

    private fun getBytesFromUri(uri: Uri): ByteArray? {
        try {
            val inputStream = app.contentResolver.openInputStream(uri)
            val byteArrayOutPutStream = ByteArrayOutputStream()
            val bufferSize = 1024
            val buffer = ByteArray(bufferSize)
            var len = inputStream?.read(buffer) ?: -1
            while (len != -1) {
                byteArrayOutPutStream.write(buffer, 0, len)
                len = inputStream?.read(buffer) ?: -1
            }
            return byteArrayOutPutStream.toByteArray()
        } catch (e: Exception) {
        }
        return null
    }

    private fun getDronesTravels(
        drones: Map<String, Int>,
        locations: MutableMap<String, Int>
    ): List<Map<String, List<String>>> {
        val totalTravels = mutableListOf<Map<String, List<String>>>()
        // I used locations as Mutable list in order to delete the already used locations
        while (locations.isNotEmpty()) {
            // this copy was created to avoid calling an object that does not exist
            val locationsCopy = locations.toMap()
            val travels = drones.map { it.key to mutableListOf<String>() }.toMap()
            val space = drones.toMutableMap()
            locationsCopy.forEach { location ->
                val items = space.filter { it.value >= location.value }.toMap()
                if (locations.containsKey(location.key) && items.isNotEmpty()) {
                    val key = items.keys.toTypedArray()[0]
                    space[key] = space[key]!!.minus(location.value)
                    travels[key]!!.add(location.key)
                    locations.remove(location.key)
                }
                if (space.map { it.value }.sum() > 0)
                    return@forEach
            }
            totalTravels.add(travels)
        }
        return totalTravels
    }
}
