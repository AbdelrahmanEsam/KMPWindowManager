package com.apptikar.windowmanager

import androidx.window.layout.FoldingFeature
import com.apptikar.windowmanager.utils.adaptive.AppOcclusionType

fun FoldingFeature.OcclusionType.toAppOcclusionType(): AppOcclusionType {
    return when (this.toString().lowercase()) {
        "full" -> AppOcclusionType.Full
        else -> AppOcclusionType.None
    }
}