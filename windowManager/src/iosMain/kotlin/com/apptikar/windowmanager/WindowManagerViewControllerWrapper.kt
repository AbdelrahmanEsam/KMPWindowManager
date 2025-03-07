package com.apptikar.windowmanager

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import com.apptikar.windowmanager.utils.adaptive.getScreenClassifier
import com.apptikar.windowmanager.utils.adaptive.sendWindowSize
import com.apptikar.windowmanager.utils.adaptive.setupObservers
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGSize
import platform.UIKit.UIViewController
import platform.UIKit.UIViewControllerTransitionCoordinatorProtocol
import platform.UIKit.addChildViewController
import platform.UIKit.didMoveToParentViewController

@OptIn(ExperimentalForeignApi::class)
class WindowManagerViewControllerWrapper(
    val content: @Composable () -> Unit,
) : UIViewController(nibName = null, bundle = null) {

    private val composeController: UIViewController = ComposeUIViewController {
        content()
    }

    init {
        sendWindowSize()
        setupObservers()
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun viewDidLoad() {
        super.viewDidLoad()
        addChildViewController(composeController)
        composeController.view.setFrame(view.bounds)
        view.addSubview(composeController.view)
        composeController.didMoveToParentViewController(this)
    }

    override fun viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        composeController.view.setFrame(view.bounds)
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun viewWillTransitionToSize(
        size: CValue<CGSize>,
        withTransitionCoordinator: UIViewControllerTransitionCoordinatorProtocol,
    ) {
        var width: Int
        var height: Int
        size.useContents {
            width = this.width.toInt()
            height = this.height.toInt()
        }
        getScreenClassifier(width, height)
        super.viewWillTransitionToSize(size, withTransitionCoordinator)
    }
}
