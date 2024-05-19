package com.mappls.sdk.demo.sample.activities.polines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.mappls.sdk.demo.MapplsMap
import com.mappls.sdk.demo.kotlin.utility.SemiCirclePointsListHelper
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng
import com.mappls.sdk.maps.geometry.LatLngBounds
import com.mappls.sdk.maps.style.sources.GeoJsonOptions
import com.mappls.sdk.plugin.annotation.LineManager
import com.mappls.sdk.plugin.annotation.LineOptions

class SemiCirclePolylineActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemiCirclePolylineScreen()
        }
    }
}


@Composable
fun SemiCirclePolylineScreen() {
    val listOfLatLng = SemiCirclePointsListHelper.showCurvedPolyline(
        LatLng(28.7039, 77.101318),
        LatLng(28.704248, 77.102370),
        0.5
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Add Polyline") })
    }) {
        Column {

            MapplsMap(onSuccess = { mapView, mapplsMap ->
                val latLngBounds = LatLngBounds.Builder()
                    .includes(listOfLatLng)
                    .build()

                mapplsMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 100))
                mapplsMap.getStyle {
                    val lineManager = LineManager(
                        mapView,
                        mapplsMap,
                        it,
                        GeoJsonOptions().withLineMetrics(true).withBuffer(2)
                    )
                    lineManager.lineDasharray = arrayOf(4f, 6f)
                    val lineOptions: LineOptions = LineOptions()
                        .points(listOfLatLng)
                        .lineColor("#FF0000")
                        .lineWidth(4f)
                    lineManager.create(lineOptions)

                }
            },
                onError = { _, _ ->

                })
        }
    }
}

