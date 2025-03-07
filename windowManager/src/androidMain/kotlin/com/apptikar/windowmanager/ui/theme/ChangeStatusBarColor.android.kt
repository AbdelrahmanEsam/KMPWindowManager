package com.apptikar.windowmanager.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.update


@Composable
actual fun ChangeStatusBarColor(color: Color) {
    statusBarColor.update {
        color
    }
}

@Composable
fun ComponentActivity.SystemBarsColorChanger() {
    val statusBarColor = statusBarColor.collectAsStateWithLifecycle()
    val navigationBarColor = navigationBarColor.collectAsStateWithLifecycle()
    enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.light(
            statusBarColor.value.toArgb(),
            statusBarColor.value.toArgb()
        ),
        navigationBarStyle = SystemBarStyle.light(
            navigationBarColor.value.toArgb(),
            navigationBarColor.value.toArgb()
        )
    )
}