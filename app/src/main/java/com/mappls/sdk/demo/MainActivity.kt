package com.mappls.sdk.demo

import MapplePinCameraFeatureActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mappls.sdk.demo.sample.activities.camera.CameraFeaturesActivity
import com.mappls.sdk.demo.sample.activities.camera.LocationCameraOptionActivity
import com.mappls.sdk.demo.sample.activities.mapEvent.MapActivity
import com.mappls.sdk.demo.sample.activities.mapEvent.MapClickActivity
import com.mappls.sdk.demo.sample.activities.maplayer.HeatmapActivity
import com.mappls.sdk.demo.sample.activities.maplayer.IndoorActivity
import com.mappls.sdk.demo.sample.activities.maplayer.ScalebarActivity
import com.mappls.sdk.demo.sample.activities.marker.AddMarkerActivity
import com.mappls.sdk.demo.sample.activities.polines.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                "Main Navigation",
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem.MapEvents,
                    MenuItem.MapLayers,
                    MenuItem.Camera,
                    MenuItem.Marker,
//                    MenuItem.Location,
                    MenuItem.PolyLines,
//                    MenuItem.RestAPI,
//                    MenuItem.Animations,
//                    MenuItem.CustomWidgets
                ),
                onItemClick = {
                    Log.e("MainScreen", "title: ${it.title}")
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                    scope.launch {
                        scaffoldState.drawerState.close()
                    }

                },
                navController = navController,
            )
        }
    ) {
        Navigation(navController = navController)
    }


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = MenuItem.MapEvents.route) {

        composable(MenuItem.MapEvents.route) {
            val list = listOf(
                ListItem("Navigate Location","", LocationCameraOptionActivity::class.java),
             //   ListItem("Add Map","",  MapActivity::class.java),
//                ListItem("Add Marker","", AddMarkerActivity::class.java),
//                ListItem("Add Polyline","", AddPolylineActivity::class.java),
             //   ListItem("Map Click","", MapClickActivity::class.java),
                )
            UiInit(list)
        }

        composable(MenuItem.MapLayers.route) {
            val list = listOf(
                ListItem("Show Heatmap data","", HeatmapActivity::class.java),
                ListItem("Indoor","", IndoorActivity::class.java),
                ListItem("Map Scale bar","", ScalebarActivity::class.java),
//                ListItem("Map Safety Strip","", MapActivity::class.java),
//                ListItem("Geo analytics plugin","", MapActivity::class.java),
//                ListItem("Driving Range Plugin","", MapActivity::class.java),
            )
            UiInit(list)
        }

        composable(MenuItem.Camera.route) {
            val list = listOf(
                ListItem("Camera Features","", CameraFeaturesActivity::class.java),
                ListItem("Location Camera Options","", LocationCameraOptionActivity::class.java),
                ListItem("Camera Features in Mappls Pin","", MapplePinCameraFeatureActivity::class.java),
            )
            UiInit(list)        }

        composable(MenuItem.Marker.route) {
            val list = listOf(
                ListItem("Add Marker","", AddMarkerActivity::class.java),
//                ListItem("Add Custom Infowindow","", MapActivity::class.java),
//                ListItem("Add Custom Marker","", MapActivity::class.java),
//                ListItem("Marker Dragging","", MapActivity::class.java),
//                ListItem("Add Marker Using Mappls Pin","", MapActivity::class.java),
//                ListItem("Add Custom Marker Using Mappls Pin","", MapActivity::class.java),
//                ListItem("Cluster Marker","", MapActivity::class.java),

            )
            UiInit(list)        }

//        composable(MenuItem.Location.route){
//            val list = listOf(
//                ListItem("Current Location","", MapActivity::class.java),
//                ListItem("Customize Current Location Icon","", MapActivity::class.java),
//            )
//            UiInit(list)
//        }

        composable(MenuItem.PolyLines.route) {
            val list = listOf(
                ListItem("Draw Polyline","", AddPolylineActivity::class.java),
                ListItem("Polyline with Gradient color","", GradientPolylineActivity::class.java),
                ListItem("Semicircle polyline","", SemiCirclePolylineActivity::class.java),
                ListItem("SnakePolyLine","", SnakeMotionPolylineActivity::class.java),
                ListItem("Draw Polygon","", PolygonActivity::class.java),
                )
            UiInit(list)        }
//        composable(MenuItem.RestAPI.route) {
//            val list = listOf(
//                ListItem("Autosuggest","", MapActivity::class.java),
//                ListItem("Geo Code","", MapActivity::class.java),
//                ListItem("Reverse Geocode","", MapActivity::class.java),
//                ListItem("Nearby","", MapActivity::class.java),
//                ListItem("Get Direction","", MapActivity::class.java),
//                ListItem("Get Distance","", MapActivity::class.java),
//                ListItem("Hateos Nearby Api","", MapActivity::class.java),
//                ListItem("POI Along Route Api","", MapActivity::class.java),
//                ListItem("Place Detail","", MapActivity::class.java),
//                ListItem("Nearby Report","", MapActivity::class.java),
//            )
//            UiInit(list)        }
//        composable(MenuItem.Animations.route) {
//            val list = listOf(
//                ListItem("Animate Car","", MapActivity::class.java),
//                ListItem("Marker Rotation and Transition","", MapActivity::class.java),
//                ListItem("Tracking sample","", MapActivity::class.java),
//            )
//            UiInit(list)        }
//        composable(MenuItem.CustomWidgets.route) {
//            val list = listOf(
//                ListItem("Place Autocomplete Widget","", MapActivity::class.java),
//                ListItem("Mappls Safety Plugin","", MapActivity::class.java),
//                ListItem("Place Picker","", MapActivity::class.java),
//                ListItem("GeoFence","", MapActivity::class.java),
//                ListItem("Direction Step","", MapActivity::class.java),
//                ListItem("Direction Widget","", MapActivity::class.java),
//                ListItem("Nearby Widget","", MapActivity::class.java),
//            )
//            UiInit(list)        }
    }

}


@Composable
fun UiInit(list: List<ListItem>) {
    val context = LocalContext.current
    LazyColumn() {
        items(list) { item->
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), onClick = {
                    context.startActivity(Intent(context,  item.destination))
                }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Text(text =item.name, color = Color.Black)
            }
        }
    }

}

data class  ListItem(val name: String, val description: String, val destination:  Class<*>)