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
import com.mappls.sdk.demo.plugin.SnakePolyLinePlugin
import com.mappls.sdk.geojson.Point
import com.mappls.sdk.geojson.utils.PolylineUtils
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng
import com.mappls.sdk.maps.geometry.LatLngBounds
import com.mappls.sdk.services.api.OnResponseCallback
import com.mappls.sdk.services.api.directions.DirectionsCriteria
import com.mappls.sdk.services.api.directions.MapplsDirectionManager
import com.mappls.sdk.services.api.directions.MapplsDirections
import com.mappls.sdk.services.api.directions.models.DirectionsResponse
import com.mappls.sdk.services.utils.Constants

class SnakeMotionPolylineActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnakeMotionPolylineScreen()
        }
    }
}


@Composable
fun SnakeMotionPolylineScreen() {
    val ORIGIN_POINT = Point.fromLngLat(77.2667594, 28.5506561)

    val DESTINATION_POINT = Point.fromLngLat(77.101318, 28.703900)
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Add Polyline") })
    }) {
        Column {

            MapplsMap(onSuccess = { mapView, mapplsMap ->

                mapplsMap.getStyle {
                    val snakePolyLinePlugin = SnakePolyLinePlugin(mapView, mapplsMap)
                    val directions = MapplsDirections.builder()
                        .origin(ORIGIN_POINT)
                        .steps(true)
                        .resource(DirectionsCriteria.RESOURCE_ROUTE_ETA)
                        .profile(DirectionsCriteria.PROFILE_DRIVING)
                        .overview(DirectionsCriteria.OVERVIEW_FULL)
                        .destination(DESTINATION_POINT)
                        .build()

                    MapplsDirectionManager.newInstance(directions).call(object :
                        OnResponseCallback<DirectionsResponse> {
                        override fun onSuccess(directionsResponse: DirectionsResponse?) {
                            if (directionsResponse != null) {
                                //handle response
                                val currentRoute = directionsResponse.routes()[0]
                                val points = PolylineUtils.decode(
                                    currentRoute.geometry()!!,
                                    Constants.PRECISION_6
                                )
                                val latLngs: MutableList<LatLng> = ArrayList()
                                for (point in points) {
                                    latLngs.add(LatLng(point.latitude(), point.longitude()))
                                }
                                val latLngBounds = LatLngBounds.Builder()
                                    .includes(latLngs)
                                    .build()
                                mapplsMap.moveCamera(
                                    CameraUpdateFactory.newLatLngBounds(
                                        latLngBounds,
                                        10,
                                        10,
                                        10,
                                        10
                                    )
                                )

                                snakePolyLinePlugin.create(currentRoute.legs()!![0].steps())

                            }
                        }

                        override fun onError(p0: Int, p1: String?) {

                        }
                    })

                }
            },
                onError = { _, _ ->

                })
        }
    }
}


