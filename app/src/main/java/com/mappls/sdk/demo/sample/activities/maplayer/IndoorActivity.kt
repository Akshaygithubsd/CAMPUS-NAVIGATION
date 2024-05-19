package com.mappls.sdk.demo.sample.activities.maplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mappls.sdk.demo.MapplsMap
import com.mappls.sdk.demo.R
import com.mappls.sdk.demo.plugin.HeatMapPlugin
import com.mappls.sdk.geojson.Point
import com.mappls.sdk.maps.annotations.Icon
import com.mappls.sdk.maps.annotations.IconFactory
import com.mappls.sdk.maps.annotations.MarkerOptions
import com.mappls.sdk.maps.camera.CameraPosition
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng

class IndoorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndoorScreen()
        }
    }
}

@Composable
fun IndoorScreen() {


    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Indoor") })
    }) {
        Column {
            val context = LocalContext.current
            MapplsMap(
                onSuccess = { mapView, mapplsMap ->
                    //To turn on layer control
                    mapplsMap.uiSettings?.isLayerControlEnabled = true

                    val iconFactory: IconFactory = IconFactory.getInstance(context)
                    val icon: Icon = iconFactory.fromResource(R.drawable.placeholder)
                    mapplsMap.addMarker(
                        MarkerOptions()
                        .position(LatLng(28.5425071, 77.1560724))
                        .icon(icon))

                    val cameraPosition: CameraPosition = CameraPosition.Builder()
                        .target(LatLng(28.5425071, 77.1560724))
                        .zoom(16.0)
                        .tilt(0.0)
                        .build()

                    mapplsMap.cameraPosition = cameraPosition
            },
                onError = { _, _ ->

                })
        }
    }
}

