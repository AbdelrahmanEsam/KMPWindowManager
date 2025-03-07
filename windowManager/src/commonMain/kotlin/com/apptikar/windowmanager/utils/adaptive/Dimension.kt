package com.apptikar.windowmanager.utils.adaptive

import kotlinx.serialization.Serializable


@Serializable
data class Dimension(val dp: Int,val sizeClass: WindowSizeClass)