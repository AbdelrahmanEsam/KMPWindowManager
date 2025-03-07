package com.apptikar.windowmanager.ui.adaptive

import com.apptikar.windowmanager.utils.adaptive.ScreenClassifier


sealed class AdaptiveLayoutScreenType {
    data class ScreenOnly(val screenClassifier: ScreenClassifier) :
        AdaptiveLayoutScreenType()
    data class ListOneThirdAndDetailThirds(val screenClassifier: ScreenClassifier.FullyOpened) :
        AdaptiveLayoutScreenType()

    data class ListHalfAndDetailHalf(val screenClassifier: ScreenClassifier) :
        AdaptiveLayoutScreenType()

    data class ListAndDetailStacked(val screenClassifier: ScreenClassifier) :
        AdaptiveLayoutScreenType()
}
