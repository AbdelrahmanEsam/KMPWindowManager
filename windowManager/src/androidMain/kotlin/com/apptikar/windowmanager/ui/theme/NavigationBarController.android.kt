package com.apptikar.windowmanager.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.update

@Composable
actual fun ChangeBottomNavigationColor(color: Color) {
    navigationBarColor.update { color }
}