package com.apptikar.adaptive

import com.apptikar.windowmanager.WindowManagerViewControllerWrapper
import com.apptikar.windowmanager.ui.theme.ChangeBottomNavigationColor
import com.apptikar.windowmanager.ui.theme.toComposeColor
import platform.UIKit.UIColor

fun MainViewController() = WindowManagerViewControllerWrapper {
    ChangeBottomNavigationColor(UIColor.greenColor.toComposeColor())
    App()
}


