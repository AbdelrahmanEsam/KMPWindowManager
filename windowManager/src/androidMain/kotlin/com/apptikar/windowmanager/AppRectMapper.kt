package com.apptikar.windowmanager

import android.graphics.Rect
import com.apptikar.windowmanager.utils.adaptive.AppRect


fun Rect.toAppRect(): AppRect = AppRect(top, bottom, left, right)