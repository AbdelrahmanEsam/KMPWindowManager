package com.apptikar.windowmanager

import android.content.res.Resources
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowLayoutInfo
import com.apptikar.windowmanager.utils.adaptive.Dimension
import com.apptikar.windowmanager.utils.adaptive.ScreenClassifier
import com.apptikar.windowmanager.utils.adaptive.WindowSizeClass
import kotlinx.serialization.Serializable

@Serializable
class ScreenInfo {
    fun screenClassifier(devicePosture: WindowLayoutInfo): ScreenClassifier {
        val height = getScreenHeightDp()
        val width = getScreenWidthDp()
        val foldingFeature = devicePosture.displayFeatures.find {
            it is FoldingFeature
        } as? FoldingFeature

        return if (foldingFeature == null) {
            createFullyOpenedDevice(height, width)
        } else if (isBookMode(foldingFeature)) {
            createBookModeObject(foldingFeature)
        } else if (isTableTopMode(foldingFeature)) {
            createTableTopModeObject(foldingFeature)
        } else {
            createFullyOpenedDevice(height, width)
        }
    }


    private fun createFullyOpenedDevice(
        windowSizeHeight: Int,
        windowSizeWidth: Int
    ): ScreenClassifier {
        val windowHeightSizeClass = when {
            windowSizeHeight < 480 -> WindowSizeClass.Compact
            windowSizeHeight < 900 -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }


        val windowWidthSizeClass = when {
            windowSizeWidth < 480 -> WindowSizeClass.Compact
            windowSizeWidth < 900 -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }

        return ScreenClassifier.FullyOpened(
            height = Dimension(windowSizeHeight, windowHeightSizeClass),
            width = Dimension(windowSizeWidth, windowWidthSizeClass)
        )
    }

    private fun createBookModeObject(foldingFeature: FoldingFeature): ScreenClassifier.HalfOpened.BookMode {
        val screenWidth = foldingFeature.bounds.left + foldingFeature.bounds.right
        val ratio = foldingFeature.bounds.left.toFloat() / screenWidth.toFloat()
        return ScreenClassifier.HalfOpened.BookMode(
            hingePosition = foldingFeature.bounds.toAppRect(),
            isSeparating = foldingFeature.isSeparating,
            hingeSeparationRatio = ratio,
            occlusionType = foldingFeature.occlusionType.toAppOcclusionType()
        )
    }

    private fun createTableTopModeObject(foldingFeature: FoldingFeature): ScreenClassifier.HalfOpened.TableTopMode {
        val screenWidth = foldingFeature.bounds.bottom + foldingFeature.bounds.top
        val ratio = foldingFeature.bounds.top.toFloat() / screenWidth.toFloat()
        return ScreenClassifier.HalfOpened.TableTopMode(
            hingePosition = foldingFeature.bounds.toAppRect(),
            isSeparating = foldingFeature.isSeparating,
            hingeSeparationRatio = ratio,
            occlusionType = foldingFeature.occlusionType.toAppOcclusionType()
        )
    }


    private fun isBookMode(foldingFeature: FoldingFeature) =
        foldingFeature.state == FoldingFeature.State.HALF_OPENED && foldingFeature.orientation == FoldingFeature.Orientation.VERTICAL

    private fun isTableTopMode(foldingFeature: FoldingFeature) =
        foldingFeature.state == FoldingFeature.State.HALF_OPENED && foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL


    private fun getScreenWidthDp(): Int {
        return Resources.getSystem().configuration.screenWidthDp
    }

    private fun getScreenHeightDp(): Int {
        return Resources.getSystem().configuration.screenHeightDp
    }
}