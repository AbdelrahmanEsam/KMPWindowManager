package com.apptikar.adaptive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.apptikar.windowmanager.ui.theme.SystemBarsColorChanger
import com.apptikar.windowmanager.utils.adaptive.clearScreenObserver
import com.apptikar.windowmanager.utils.adaptive.observeScreenChanges

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeScreenChanges()
        setContent {
            SystemBarsColorChanger()
            App()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        clearScreenObserver()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}