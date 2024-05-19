package com.mappls.sdk.demo.sample.activities.mapEvent

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mappls.sdk.demo.MapplsMap
import com.mappls.sdk.maps.camera.CameraPosition
import com.mappls.sdk.maps.geometry.LatLng

class MapClickActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapClickScreen()
        }
    }
}

@Composable
fun MapClickScreen() {
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Map Click") })
    }) {
        Column {

            MapplsMap(onSuccess = { mapView, mapplsMap ->
                mapplsMap.cameraPosition = CameraPosition.Builder()
                    .target(LatLng(27.0, 78.0))
                    .zoom(14.0)
                    .build()
                mapplsMap.addOnMapClickListener { latlng ->
                    Toast.makeText(
                        context,
                        "${latlng.latitude}, ${latlng.longitude}",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@addOnMapClickListener false
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    MapClickScreen()
}