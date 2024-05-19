import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mappls.sdk.demo.R
import com.mappls.sdk.demo.sample.activities.camera.locationEngine
import com.mappls.sdk.maps.MapplsMap
import com.mappls.sdk.maps.Style
import com.mappls.sdk.maps.location.LocationComponent
import com.mappls.sdk.maps.location.LocationComponentActivationOptions
import com.mappls.sdk.maps.location.LocationComponentOptions
import com.mappls.sdk.maps.location.engine.LocationEngine
import com.mappls.sdk.maps.location.engine.LocationEngineCallback
import com.mappls.sdk.maps.location.engine.LocationEngineRequest
import com.mappls.sdk.maps.location.engine.LocationEngineResult
import com.mappls.sdk.maps.location.modes.CameraMode
import com.mappls.sdk.maps.location.modes.RenderMode

class CurrentLocation() {

    private lateinit var locationEngine: LocationEngine
    private lateinit var locationComponent: LocationComponent

    private val locationEngineCallback = object : LocationEngineCallback<LocationEngineResult> {
        override fun onSuccess(result: LocationEngineResult?) {
            result?.lastLocation?.let { location ->
                // Handle the location update
            }
        }

        override fun onFailure(exception: Exception) {
            // Handle failure
        }
    }

    fun activateLocationComponent(map: MapplsMap,context: Context) {
        val options = LocationComponentOptions.builder(context)
            .trackingGesturesManagement(true)
            .accuracyColor(ContextCompat.getColor(context, R.color.purple_500))
            .build()

        locationComponent = map.locationComponent
        val locationComponentActivationOptions =
            map.style?.let {
                LocationComponentActivationOptions.builder(context, it)
                    .locationComponentOptions(options)
                    .build()
            }

        if (locationComponentActivationOptions != null) {
            locationComponent.activateLocationComponent(locationComponentActivationOptions)
        }
        locationComponent.isLocationComponentEnabled = true

        locationEngine = locationComponent.locationEngine!!
        val request = LocationEngineRequest.Builder(1000)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationEngine.requestLocationUpdates(request, locationEngineCallback, Looper.getMainLooper())

        locationComponent.cameraMode = CameraMode.TRACKING
        locationComponent.renderMode = RenderMode.COMPASS
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        if (::locationEngine.isInitialized) {
//            locationEngine.removeLocationUpdates(locationEngineCallback)
//        }
//    }
}
