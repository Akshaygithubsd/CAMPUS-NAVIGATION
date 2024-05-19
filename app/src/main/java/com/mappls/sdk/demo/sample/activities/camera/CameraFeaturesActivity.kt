package com.mappls.sdk.demo.sample.activities.camera

import CurrentLocation
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mappls.sdk.demo.MapplsMap
import com.mappls.sdk.demo.R
import com.mappls.sdk.maps.MapplsMap
import com.mappls.sdk.maps.camera.CameraPosition
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng

class CameraFeaturesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraFeaturesScreen()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

@Composable
fun CameraFeaturesScreen() {
    lateinit var currentlocation: CurrentLocation;
    var mapplsMap1: MapplsMap? = null
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Camera Features") })
    }) {

        Column(

        ) {

            MapplsMap(
               modifier = Modifier.weight(1f),
                onSuccess = { mapView, mapplsMap ->
                    mapplsMap1 = mapplsMap
                    //val currentLocation = CurrentLocation(context, mapplsMap1!!)
                    val cameraPosition = CameraPosition.Builder().target(
                        LatLng(
                            25.321684, 82.987289
                        )
                    ).zoom(14.0).tilt(0.0).build()
                    mapplsMap.cameraPosition = cameraPosition
                },

                onError = { _, _ ->

                })


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.purple_500)).padding(8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Move Camera", color = Color.White, modifier = Modifier
                    .clickable(enabled = true) {
                        mapplsMap1?.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(LatLng(
                                22.553147478403194,
                                77.23388671875), 14.0))
                    },)
                Text(text = "Ease Camera", color = Color.White,modifier = Modifier
                    .clickable(enabled = true) {
                        mapplsMap1?.easeCamera(CameraUpdateFactory.newLatLngZoom(LatLng(
                            28.704268, 77.103045), 14.0))
                    },)
                Text(text = "Animate Camera", color = Color.White,modifier = Modifier
                    .clickable(enabled = true) {
                        mapplsMap1?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(
                            28.698791, 77.121243), 14.0))
                    },)
            }


        }
    }


}

