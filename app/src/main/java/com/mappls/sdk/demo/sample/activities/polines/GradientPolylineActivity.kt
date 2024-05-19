package com.mappls.sdk.demo.sample.activities.polines

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mappls.sdk.demo.MapplsMap
import com.mappls.sdk.demo.plugin.GradientPolylinePlugin
import com.mappls.sdk.maps.annotations.PolylineOptions
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng
import com.mappls.sdk.maps.geometry.LatLngBounds
import com.mappls.sdk.plugin.annotation.LineManager
import com.mappls.sdk.plugin.annotation.LineOptions

class GradientPolylineActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradientPolylineScreen()
        }
    }
}


@Composable
fun GradientPolylineScreen() {
    val listOfLatLng = listOf<LatLng>(
        LatLng(28.705436, 77.100462),
        LatLng(28.705191, 77.100784),
        LatLng(28.704646, 77.101514),
        LatLng(28.704194, 77.101171),
        LatLng(28.704083, 77.101066),
        LatLng(28.703900, 77.101318)
    )
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Add Polyline") })
    }) {
        Column {

            MapplsMap(onSuccess = { mapView, mapplsMap ->

                mapplsMap.setPadding(20, 20, 20, 20)
                val latLngBounds = LatLngBounds.Builder()
                    .includes(listOfLatLng)
                    .build()
                mapplsMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 10))
                val animatedPolylinePlugin = GradientPolylinePlugin(mapplsMap, mapView)
                animatedPolylinePlugin.createPolyline(listOfLatLng)
            },
                onError = { _, _ ->

                })
        }
    }
}
