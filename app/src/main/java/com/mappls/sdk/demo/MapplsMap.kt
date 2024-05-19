package com.mappls.sdk.demo

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.mappls.sdk.maps.MapView
import com.mappls.sdk.maps.MapplsMap
import com.mappls.sdk.maps.OnMapReadyCallback
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng

@Composable
fun MapplsMap(
    modifier: Modifier = Modifier,
    onSuccess: ((MapView, MapplsMap) -> Unit)? = null,
    onError: ((Int, String?) -> Unit)? = null
) {
    val mapView = MapView()
    AndroidView(
        factory = { mapView },
        modifier = modifier
    ) {
        it.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(p0: MapplsMap) {
                onSuccess?.invoke(it, p0)
            }

            override fun onMapError(p0: Int, p1: String?) {
                onError?.invoke(p0, p1)
            }
        })

    }
}

@Composable
private fun MapView(): MapView {
    val context = LocalContext.current

    val mapView = remember {
        MapView(context)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle, mapView) {
        // Make MapView follow the current lifecycle
        val lifecycleObserver = getMapLifecycleObserver(mapView)
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            mapView.onResume()
	        mapView.onStop()
	        mapView.onDestroy()
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

/**
 * Handles lifecycle of provided mapView
 */
private fun getMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
            Lifecycle.Event.ON_START -> mapView.onStart()
            Lifecycle.Event.ON_RESUME -> mapView.onResume()
            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
            Lifecycle.Event.ON_STOP -> mapView.onStop()
            Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
            else -> throw IllegalStateException()
        }
    }
