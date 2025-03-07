package com.apptikar.windowmanager.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIApplication
import platform.UIKit.UIScreen
import platform.UIKit.UIView
import platform.UIKit.UIWindow


@Composable
actual fun ChangeStatusBarColor(color: Color) {
    val statusBar = statusBarView()
    SideEffect {
        statusBar.backgroundColor = color.toUIColor()
    }
}


@OptIn(ExperimentalForeignApi::class)
@Composable
private fun statusBarView() = remember {
    val keyWindow: UIWindow? =
        UIApplication.sharedApplication.windows.firstOrNull { (it as? UIWindow)?.isKeyWindow() == true } as? UIWindow
    val safeAreaInsets = UIApplication.sharedApplication.keyWindow?.safeAreaInsets
    val width = UIScreen.mainScreen.bounds.useContents { this.size.width }
    var topInsets = 0.0



    safeAreaInsets?.let {
        topInsets = safeAreaInsets.useContents {
            this.top
        }
    }
    val tag = 3848245L


    val statusBarView = UIView(frame = CGRectMake(0.0, 0.0, width, topInsets))

    keyWindow?.viewWithTag(tag) ?: run {
        statusBarView.tag = tag
        statusBarView.layer.zPosition = 999999.0
        keyWindow?.addSubview(statusBarView)
        statusBarView
    }


}