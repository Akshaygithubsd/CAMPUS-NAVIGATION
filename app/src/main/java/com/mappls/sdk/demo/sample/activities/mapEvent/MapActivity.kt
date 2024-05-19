package com.mappls.sdk.demo.sample.activities.mapEvent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mappls.sdk.demo.MapplsMap
import com.mappls.sdk.demo.theme.VectorMapComposeSampleTheme

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapScreen()
        }
    }
}

@Composable
fun MapScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = {Text(text = "Map Activity")})
        }
    ) {
        Column {
            
            MapplsMap()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    VectorMapComposeSampleTheme {
        MapScreen()
    }
}