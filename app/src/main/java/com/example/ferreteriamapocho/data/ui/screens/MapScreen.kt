package com.example.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen() {

    val ferreteriaLocation = LatLng(-33.43685, -70.71840)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ferreteriaLocation, 17f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = ferreteriaLocation),
            title = "Ferretería Mapocho",
            snippet = "Mapocho , Quinta Normal"
        )
    }
}
