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
import com.mappls.sdk.demo.plugin.HeatMapPlugin
import com.mappls.sdk.geojson.Point
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng

class HeatmapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeatmapScreen()
        }
    }
}

@Composable
fun HeatmapScreen() {

    val heatMapOptionList = listOf(
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(77.5129, 28.1016), 2.3),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(77.5132, 28.1021), 2.0),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(76.4048, 28.1224), 1.7),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(76.4052, 28.1232), 1.7),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(77.3597, 28.0781), 1.6),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(77.3597, 28.0781), 1.6),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(74.789, 28.1725), 2.4),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(77.512, 28.0879), 1.8),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(74.2832, 28.674242), 1.3),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(80.8244288, 24.6778728), 2.4),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(81.637417, 24.6778728), 3.2),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(77.7372706, 27.8302397), 1.2),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(79.2973292, 28.3729432), 4.2),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(79.7477686, 26.2351935), 2.13),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(80.1762354, 26.1760509), 3.2),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(79.7367823, 27.333618), 1.2),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(80.9452784, 26.628706), 5.2),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(78.6930811, 28.9032672), 3.2),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(78.73702641, 29.3255866), 4.0),
        HeatMapPlugin.HeatMapOption(Point.fromLngLat(79.4181788, 29.5838759), 5.0)
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Heat Map") })
    }) {
        Column {
            MapplsMap(
                onSuccess = { mapView, mapplsMap ->
                val heatMapPlugin: HeatMapPlugin = HeatMapPlugin.builder(mapplsMap, mapView)
                    .addAll(heatMapOptionList)
                    .build()
                heatMapPlugin.addHeatmap()
                mapplsMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(28.0, 77.0),
                        4.0
                    )
                )
            },
                onError = { _, _ ->

                })
        }
    }
}

