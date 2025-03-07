package com.apptikar.windowmanager.utils.adaptive

import kotlinx.serialization.Serializable

@Serializable
sealed interface ScreenClassifier {

    @Serializable
    data class FullyOpened(val width: Dimension, val height: Dimension) : ScreenClassifier


    @Serializable
    sealed interface HalfOpened : ScreenClassifier {
        val hingePosition: AppRect
        val hingeSeparationRatio: Float
        val isSeparating: Boolean
        val occlusionType: AppOcclusionType


        @Serializable
        data class BookMode(
            override val hingePosition: AppRect,
            override val hingeSeparationRatio: Float,
            override val isSeparating: Boolean,
            override val occlusionType: AppOcclusionType
        ) : HalfOpened


        @Serializable
        data class TableTopMode(
            override val hingePosition: AppRect,
            override val hingeSeparationRatio: Float,
            override val isSeparating: Boolean,
            override val occlusionType: AppOcclusionType
        ) : HalfOpened

    }
}