package com.apptikar.windowmanager.utils.adaptive

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import platform.CoreGraphics.CGRect
import platform.Foundation.NSNotificationCenter
import platform.UIKit.UIDeviceOrientationDidChangeNotification
import platform.UIKit.UIScreen
import platform.UIKit.UIWindowDidBecomeKeyNotification

private val _screenData: MutableStateFlow<ScreenClassifier?> = MutableStateFlow(null)
actual val screenData: StateFlow<ScreenClassifier?> = _screenData.asStateFlow()


fun getScreenClassifier(width: Int, height: Int) {
    val windowHeightSizeClass = when {
        width < 480 -> WindowSizeClass.Compact
        height < 900 -> WindowSizeClass.Medium
        else -> WindowSizeClass.Expanded
    }


    val windowWidthSizeClass = when {
        width < 480 -> WindowSizeClass.Compact
        height < 900 -> WindowSizeClass.Medium
        else -> WindowSizeClass.Expanded
    }
    _screenData.update {
        ScreenClassifier.FullyOpened(
            height = Dimension(height, windowHeightSizeClass),
            width = Dimension(width, windowWidthSizeClass)
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
fun setupObservers(
    afterEvaluation: (CValue<CGRect>) -> Unit = { _ ->

    },
) {
    val notificationCenter = NSNotificationCenter.defaultCenter

    notificationCenter.addObserverForName(
        name = UIDeviceOrientationDidChangeNotification,
        `object` = null,
        queue = null
    ) { _ ->
        sendWindowSize { bounds ->
            afterEvaluation.invoke(bounds)
        }
    }

    notificationCenter.addObserverForName(
        name = UIWindowDidBecomeKeyNotification,
        `object` = null,
        queue = null
    ) { _ ->
        sendWindowSize { bounds ->
            afterEvaluation.invoke(bounds)
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
fun sendWindowSize(
    afterEvaluation: (CValue<CGRect>) -> Unit = { _ ->

    },
) {
    var width: Int
    var height: Int
    val bounds = UIScreen.mainScreen.bounds
    afterEvaluation.invoke(bounds)
    bounds.useContents {
        width = this.size.width.toInt()
        height = this.size.height.toInt()
    }
    getScreenClassifier(width, height)
}