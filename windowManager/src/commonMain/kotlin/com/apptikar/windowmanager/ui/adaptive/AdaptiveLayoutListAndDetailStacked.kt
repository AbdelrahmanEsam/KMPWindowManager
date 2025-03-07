package com.apptikar.windowmanager.ui.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier

@Composable
fun AdaptiveLayoutListAndDetailStacked(
    hingeRation : Float = 0.5f,
    firstComposable: @Composable () -> Unit,
    secondComposable: @Composable () -> Unit
) {


    Column {

        Box(modifier = Modifier.fillMaxHeight(hingeRation)) {
            firstComposable()
        }

        Box(modifier = Modifier.fillMaxHeight()) {
            secondComposable()
        }
    }


}