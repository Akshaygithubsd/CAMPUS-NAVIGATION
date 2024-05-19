package com.mappls.sdk.demo

sealed class MenuItem(var route: String, var title: String) {
    object MapEvents : MenuItem("mapEvents", "Map Events")
    object MapLayers : MenuItem("mapLayers", "Map Layers")
    object Camera : MenuItem("camera", "Camera")
    object Marker : MenuItem("marker", "Marker")
    object Location : MenuItem("location", "Location")
    object PolyLines : MenuItem("polyLines", "PolyLines")
    object RestAPI : MenuItem("restAPI", "Rest API Calls")
    object Animations : MenuItem("animations", "Animations")
    object CustomWidgets : MenuItem("customWidgets", "Custom Widgets")

}
