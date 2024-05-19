package com.mappls.sdk.demo.sample.activities.polines

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.mappls.sdk.demo.MapplsMap
import com.mappls.sdk.maps.annotations.PolygonOptions
import com.mappls.sdk.maps.camera.CameraPosition
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng
import com.mappls.sdk.maps.geometry.LatLngBounds

class PolygonActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PolygonScreen()
        }
    }
}


@Composable
fun PolygonScreen() {
    val listOfLatlang = listOf<LatLng>(
        LatLng(28.703900, 77.101318),
        LatLng(28.703331, 77.102155),
        LatLng(28.703905, 77.102761),
        LatLng(28.704248, 77.102370),
    )
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Add Polyline") })
    }) {
        Column {

            MapplsMap(onSuccess = { mapView, mapplsMap ->


                mapplsMap.cameraPosition = setCameraAndTilt()
                mapplsMap.setPadding(20, 20, 20, 20)

                /* this is done for move camera focus to particular position */
                val latLngBounds = LatLngBounds.Builder().includes(listOfLatlang).build()
                mapplsMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 70))
                mapplsMap.addPolygon(
                    PolygonOptions().addAll(listOfLatlang).fillColor(
                        Color.parseColor("#753bb2d0")
                    )
                )


            },
                onError = { _, _ ->

                })
        }
    }
}

fun setCameraAndTilt(): CameraPosition {
    return CameraPosition.Builder().target(
        LatLng(
            28.551087, 77.257373
        )
    ).zoom(14.0).tilt(0.0).build()
}
