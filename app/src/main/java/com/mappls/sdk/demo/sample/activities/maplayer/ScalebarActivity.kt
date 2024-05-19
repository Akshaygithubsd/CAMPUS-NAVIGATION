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

import com.mappls.sdk.maps.camera.CameraPosition
import com.mappls.sdk.maps.geometry.LatLng
import com.mappls.sdk.plugin.scalebar.ScaleBarOptions
import com.mappls.sdk.plugin.scalebar.ScaleBarPlugin


class ScalebarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaleBarScreen()
        }
    }
}

@Composable
fun ScaleBarScreen() {


    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Map Scale Bar") })
    }) {
        Column {
            val context = LocalContext.current
            MapplsMap(
                onSuccess = { mapView, mapplsMap ->
                    //To turn on layer control
                    /* this is done for animating/moving camera to particular position */
                    val cameraPosition = CameraPosition.Builder().target(LatLng(
                        25.321684, 82.987289)).zoom(10.0).tilt(0.0).build()
                    mapplsMap.cameraPosition = cameraPosition

                    val scaleBarPlugin = ScaleBarPlugin(mapView, mapplsMap!!)
                    val scalebarOptions = ScaleBarOptions(context)
                        .setTextColor(android.R.color.black)
                        .setTextSize(40f)
                        .setBarHeight(5f)
                        .setBorderWidth(2f)
                        .setRefreshInterval(15)
                        .setMarginTop(R.dimen.scalebar_top_margin)
                        .setMarginLeft(R.dimen.scalebar_left_margin)
                        .setTextBarMargin(15f)
                        .setMaxWidthRatio(0.5f)
                        .setShowTextBorder(true)
                        .setTextBorderWidth(5f)
                    scaleBarPlugin.create(scalebarOptions)
            },
                onError = { _, _ ->

                })
        }
    }
}

