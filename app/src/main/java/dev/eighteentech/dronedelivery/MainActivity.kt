package dev.eighteentech.dronedelivery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.eighteentech.dronedelivery.base.BaseActivity
import dev.eighteentech.dronedelivery.ui.screens.MainScreen

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    private var droneTravels = listOf<Map<String, List<String>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            viewModel = hiltViewModel()
            MainScreen(mainViewModel = viewModel, onClick = { openDialog() })
        }
    }

    private fun openDialog() {
        var data = Intent(Intent.ACTION_OPEN_DOCUMENT)
        data.type = "text/plain"
        data = Intent.createChooser(data, "Choose a file")
        startActivityResultLauncher.launch(data)
    }

    private val startActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK)
            result.data?.data?.let { uri ->
                viewModel.getFileFromUri(uri)
            }
    }
}
