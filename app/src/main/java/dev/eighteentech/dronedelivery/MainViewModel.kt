package dev.eighteentech.dronedelivery

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _droneTravels = MutableLiveData<List<Map<String, List<String>>>>(emptyList())
    val droneTravels: LiveData<List<Map<String, List<String>>>> get() = _droneTravels

    fun getFileFromUri(uri: Uri) {
        _droneTravels.value = repository.getDronesTravelList(uri)
        Log.d("Result", repository.getDronesTravelList(uri).toString())
    }
}
