package com.apptikar.windowmanager.ui.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UINavigationBar
import platform.UIKit.UINavigationBarAppearance
import platform.UIKit.UITabBar
import platform.UIKit.UIToolbar
import platform.UIKit.UIViewController
import platform.UIKit.navigationController
import platform.UIKit.setToolbarItems
import platform.UIKit.tabBarController

@Composable
actual fun ChangeBottomNavigationColor(color: Color) {
    SideEffect {
        UIApplication.sharedApplication.keyWindow?.rootViewController?.let { rootViewController ->
            setNavigationBarColor(rootViewController, color.toUIColor())
        }
    }
}

internal fun setNavigationBarColor(viewController: UIViewController, color: UIColor) {
    viewController.view.backgroundColor = color
}