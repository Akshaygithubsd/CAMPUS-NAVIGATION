[<img src="https://about.mappls.com/images/mappls-b-logo.svg" height="60"/> </p>](https://www.mapmyindia.com/api)

# Mappls Vector Map Android SDK JetPack Compose

**Easy To Integrate Maps & Location APIs & SDKs For Web & Mobile Applications**
Powered with India’s most comprehensive and robust mapping functionalities.
**Now Available** for Srilanka, Nepal, Bhutan and Bangladesh

1. You can get your api key to be used in this document here: [https://www.mapmyindia.com/api/signup](https://www.mapmyindia.com/api/signup)

2. The sample code is provided to help you understand the basic functionality of MapmyIndia maps & REST APIs working on **Android** native development platform.

3.Explore through [200+ countries & territories](https://github.com/MapmyIndia/mapmyindia-rest-api/blob/master/docs/countryISO.md) with **Global Search, Routing and Mapping APIs & SDKs** by Mappls.

## [Getting Started](#getting-started)

Mappls Maps SDK for Android lets you easily add Mappls Maps and web services to your own Android application. The SDK for Android supports API 21+. You can have a look at the map and features you will get in your own app by using the Mappls Maps SDK for Android.

Through customized tiles, you can add different map layers to your application and add bunch of controls and gestures to enhance map usability thus creating potent map based solutions for your customers. The SDK handles downloading of map tiles and their display along with a bunch of controls and native gestures.

## [Setup your project](#setup-your-project)
Follow these steps to add the SDK to your project –
- Create a new project in Android Studio
  **For older Build versions (i.e, Before gradle v7.0.0)**
- Add Mappls repository in your project level `build.gradle`
```groovy
allprojects {
repositories {
maven {
  url 'https://maven.mappls.com/repository/mappls/'
}
}
}
```

**For Newer Build Versions (i.e, After gradle v7.0.0)**

- Add MapmyIndia repository in your `settings.gradle`

```groovy
dependencyResolutionManagement {
// repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
repositories {
  google()
  mavenCentral()
  maven {
    url 'https://maven.mappls.com/repository/mappls/'
  }
}
}
```

- Add below dependency in your app-level `build.gradle`

```groovy
implementation 'com.mappls.sdk:mappls-android-sdk:8.0.0'
implementation 'com.mappls.sdk:annotation-plugin:1.0.0'
```

- Add these permissions in your project
```xml
<uses-permission  android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission  android:name="android.permission.INTERNET"/>
```



### [Add Java 8 Support to the project](#add-java-8-support-to-the-project)
_add following lines in your app module’s build.gradle_
```groovy
compileOptions {
sourceCompatibility 1.8
targetCompatibility 1.8
}
```

### [Add your API keys to the SDK](#add-your-api-keys-to-the-sdk)

_Add your API keys to the SDK (in your application’s onCreate() or before using map)_

```kotlin
MapplsAccountManager.getInstance().restAPIKey = "SET_REST_API_KEY"
MapplsAccountManager.getInstance().mapSDKKey = "SET_MAP_KEY"
MapplsAccountManager.getInstance().atlasClientId = "SET_CLIENT_ID"
MapplsAccountManager.getInstance().atlasClientSecret = "SET_CLIENT_SECRET"
Mappls.getInstance(applicationContext)
```



_You cannot use the Mappls Map Mobile SDK without these function calls. You will find your keys in your API Dashboard._

## [Add a Mappls Map to your application](#add-a-mappls-map-to-your-application)
To make Jetpack compose Support

```kotlin
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
 **/
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
```
To use Mappls Map
```kotlin
MapplsMap(onSuccess = { mapView, mapplsMap->

},
onError = { code, message ->

})
```
**onSuccess callback provides `MapView` and `MapplsMap` which is used to perform different operations.**

## Add Marker

```kotlin
@Composable
fun AddMarkerScreen() {
Scaffold(topBar = {
TopAppBar(title = { Text(text = "Add Marker") })
}) {
  Column {
   MapplsMap(onSuccess={ mapView, mapplsMap ->
   mapplsMap.getStyle {
      val symbolManager = SymbolManager(mapView, mapplsMap, it)
      symbolManager.create(SymbolOptions().position(LatLng(27.0, 77.0)))
  }
mapplsMap.cameraPosition = CameraPosition.Builder()
.target(LatLng(27.0, 77.0))
.zoom(14.0)
.build()
},
onError = { _, _ ->

})
}
}}
```
<br><br><br>

For any queries and support, please contact:

[<img src="https://about.mappls.com/images/mappls-logo.svg" height="40"/> </p>](https://about.mappls.com/api/)
Email us at [apisupport@mappls.com](mailto:apisupport@mappls.com)


![](https://www.mapmyindia.com/api/img/icons/support.png)
[Support](https://about.mappls.com/contact/)
Need support? contact us!

<br></br>
<br></br>

[<p align="center"> <img src="https://www.mapmyindia.com/api/img/icons/stack-overflow.png"/> ](https://stackoverflow.com/questions/tagged/mappls-api)[![](https://www.mapmyindia.com/api/img/icons/blog.png)](https://about.mappls.com/blog/)[![](https://www.mapmyindia.com/api/img/icons/gethub.png)](https://github.com/Mappls-api)[<img src="https://mmi-api-team.s3.ap-south-1.amazonaws.com/API-Team/npm-logo.one-third%5B1%5D.png" height="40"/> </p>](https://www.npmjs.com/org/mapmyindia)



[<p align="center"> <img src="https://www.mapmyindia.com/june-newsletter/icon4.png"/> ](https://www.facebook.com/Mapplsofficial)[![](https://www.mapmyindia.com/june-newsletter/icon2.png)](https://twitter.com/mappls)[![](https://www.mapmyindia.com/newsletter/2017/aug/llinkedin.png)](https://www.linkedin.com/company/mappls/)[![](https://www.mapmyindia.com/june-newsletter/icon3.png)](https://www.youtube.com/channel/UCAWvWsh-dZLLeUU7_J9HiOA)




<div align="center">@ Copyright 2022 CE Info Systems Ltd. All Rights Reserved.</div>

<div align="center"> <a href="https://about.mappls.com/api/terms-&-conditions">Terms & Conditions</a> | <a href="https://about.mappls.com/about/privacy-policy">Privacy Policy</a> | <a href="https://about.mappls.com/pdf/mapmyIndia-sustainability-policy-healt-labour-rules-supplir-sustainability.pdf">Supplier Sustainability Policy</a> | <a href="https://about.mappls.com/pdf/Health-Safety-Management.pdf">Health & Safety Policy</a> | <a href="https://about.mappls.com/pdf/Environment-Sustainability-Policy-CSR-Report.pdf">Environmental Policy & CSR Report</a>

<div align="center">Customer Care: +91-9999333223</div>
