import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

//import com.mapmyindia.sdk.maps.MapView
//import com.mapmyindia.sdk.maps.MapmyIndiaMap
//import com.mapmyindia.sdk.maps.OnMapReadyCallback
//import com.mapmyindia.sdk.maps.CameraUpdateFactory
//import com.mapmyindia.sdk.maps.geometry.LatLng
//import com.mapmyindia.sdk.maps.MapmyIndiaMap



import com.mappls.sdk.maps.MapView
import com.mappls.sdk.maps.MapplsMap
import com.mappls.sdk.maps.OnMapReadyCallback
import com.mappls.sdk.maps.camera.CameraUpdateFactory
import com.mappls.sdk.maps.geometry.LatLng

class MapplePinCameraFeatureActivity : ComponentActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraScreen()
        }
    }
    override fun onMapError(p0: Int, p1: String?) {
        // Handle map error here, or leave it empty if not needed
    }
    override fun onMapReady(mapplsMap: MapplsMap) {
       // val currentLocation = CurrentLocation(this, mapplsMap)
        val cameraPosition = CameraUpdateFactory.newLatLngZoom(
            LatLng(
            25.321684, 82.987289), 14.0)
        mapplsMap.animateCamera(cameraPosition)
    }

}

@Composable
fun CameraScreen() {
    val context = LocalContext.current

    AndroidView(factory = { context ->
        MapView(context).apply {
            getMapAsync(object : OnMapReadyCallback {
                override fun onMapReady(mapmyIndiaMap: MapplsMap) {
                    (context as? MapplePinCameraFeatureActivity)?.onMapReady(mapmyIndiaMap)
                }

                override fun onMapError(p0: Int, p1: String?) {
                    // Handle map error here
                }
            })
        }
    }, modifier = Modifier.fillMaxWidth())



    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                // Implement button click actions here
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Move Camera")
        }
        // Add other buttons as needed
    }
}
