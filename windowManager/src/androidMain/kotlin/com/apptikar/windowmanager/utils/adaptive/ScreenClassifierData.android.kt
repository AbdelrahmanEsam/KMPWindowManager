package com.apptikar.windowmanager.utils.adaptive

import android.app.Activity
import androidx.window.layout.WindowInfoTracker
import com.apptikar.windowmanager.ScreenInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private lateinit var windowInfoTracker: WindowInfoTracker
private val _screenData: MutableStateFlow<ScreenClassifier?> = MutableStateFlow(null)

actual val screenData : StateFlow<ScreenClassifier?> = _screenData.asStateFlow()

private lateinit var observerJob: Job


fun Activity.observeScreenChanges() {
    windowInfoTracker = WindowInfoTracker.getOrCreate(context = this)
    observerJob = CoroutineScope(Dispatchers.IO).launch {
        windowInfoTracker.windowLayoutInfo(this@observeScreenChanges)
            .collectLatest { devicePosture ->
                val screenClassifier =
                    ScreenInfo().screenClassifier(devicePosture = devicePosture)
                _screenData.update { screenClassifier }
            }
    }
}

fun Activity.clearScreenObserver() {
    observerJob.cancel()
}