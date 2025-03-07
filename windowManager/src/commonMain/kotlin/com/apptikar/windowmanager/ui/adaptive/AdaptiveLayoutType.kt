package com.apptikar.windowmanager.ui.adaptive

import androidx.compose.runtime.Composable
import com.apptikar.windowmanager.utils.adaptive.ScreenClassifier
import com.apptikar.windowmanager.utils.adaptive.WindowSizeClass


@Composable
fun ScreenClassifier.toAdaptiveLayoutScreenType(): AdaptiveLayoutScreenType {
    // add your logic depending on the screen type
    // you can add more than one composable like this one depending on each screen UX  this is just example
    return if (this is ScreenClassifier.FullyOpened && width.sizeClass == WindowSizeClass.Expanded) {
        AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds(screenClassifier = this)
    } else if (this is ScreenClassifier.FullyOpened && width.sizeClass == WindowSizeClass.Medium && height.sizeClass != WindowSizeClass.Compact) {
        AdaptiveLayoutScreenType.ListHalfAndDetailHalf(this)
    } else if (this is ScreenClassifier.FullyOpened) {
        AdaptiveLayoutScreenType.ScreenOnly(this)
    } else if (this is ScreenClassifier.HalfOpened.BookMode) {
        AdaptiveLayoutScreenType.ListHalfAndDetailHalf(this)
    } else if (this is ScreenClassifier.HalfOpened.TableTopMode) {
        AdaptiveLayoutScreenType.ListAndDetailStacked(this)
    } else {
        AdaptiveLayoutScreenType.ScreenOnly(this)
    }
}




