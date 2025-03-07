package com.apptikar.windowmanager.ui.adaptive

import androidx.compose.runtime.Composable
import com.apptikar.windowmanager.utils.adaptive.ScreenClassifier


@Composable
fun ScreenClassifier.toAdaptiveLayoutScreenType(): AdaptiveLayoutScreenType {
    return if (this is ScreenClassifier.FullyOpened && width.sizeClass == com.apptikar.windowmanager.utils.adaptive.WindowSizeClass.Expanded) {
        AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds(screenClassifier = this)
    } else if (this is ScreenClassifier.FullyOpened && width.sizeClass == com.apptikar.windowmanager.utils.adaptive.WindowSizeClass.Medium && height.sizeClass != com.apptikar.windowmanager.utils.adaptive.WindowSizeClass.Compact) {
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

