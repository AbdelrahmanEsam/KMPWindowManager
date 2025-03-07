package com.apptikar.windowmanager.ui.theme

import androidx.compose.ui.graphics.Color
import kotlinx.cinterop.DoubleVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import platform.UIKit.UIColor


fun Color.toUIColor(): UIColor = UIColor(
    red = this.red.toDouble(),
    green = this.green.toDouble(),
    blue = this.blue.toDouble(),
    alpha = this.alpha.toDouble()
)


@OptIn(ExperimentalForeignApi::class)
fun UIColor.toComposeColor(): Color = memScoped {
    val redArray = allocArray<DoubleVar>(1)
    val greenArray = allocArray<DoubleVar>(1)
    val blueArray = allocArray<DoubleVar>(1)
    val alphaArray = allocArray<DoubleVar>(1)

    if (this@toComposeColor.getRed(redArray, green = greenArray, blue = blueArray, alpha = alphaArray)) {
        Color(
            red = redArray[0].toFloat(),
            green = greenArray[0].toFloat(),
            blue = blueArray[0].toFloat(),
            alpha = alphaArray[0].toFloat()
        )
    } else {
        Color.Black
    }
}