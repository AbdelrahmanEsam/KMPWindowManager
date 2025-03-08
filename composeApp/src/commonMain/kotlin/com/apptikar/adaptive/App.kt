package com.apptikar.adaptive

import AdaptiveLayoutListHalfAndDetailHalf
import AdaptiveLayoutListOneThirdAndDetailTwoThirds
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apptikar.windowmanager.ui.adaptive.AdaptiveLayoutListAndDetailStacked
import com.apptikar.windowmanager.ui.adaptive.AdaptiveLayoutScreenType.ListAndDetailStacked
import com.apptikar.windowmanager.ui.adaptive.AdaptiveLayoutScreenType.ListHalfAndDetailHalf
import com.apptikar.windowmanager.ui.adaptive.AdaptiveLayoutScreenType.ListOneThirdAndDetailThirds
import com.apptikar.windowmanager.ui.adaptive.AdaptiveLayoutScreenType.ScreenOnly
import com.apptikar.windowmanager.ui.adaptive.toAdaptiveLayoutScreenType
import com.apptikar.windowmanager.utils.adaptive.screenData
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        RootComposable(modifier = Modifier.fillMaxSize())
    }
}


@Composable
fun RootComposable(modifier: Modifier = Modifier) {
    val screenData = screenData.collectAsStateWithLifecycle()
    val adaptiveType =screenData.value?.toAdaptiveLayoutScreenType()
    when (adaptiveType) {
        is ScreenOnly -> {
            FirstScreen(modifier = Modifier.fillMaxSize().background(color = Color.Cyan))
        }

        is ListOneThirdAndDetailThirds -> {
            AdaptiveLayoutListOneThirdAndDetailTwoThirds(
                firstComposable = {
                    FirstScreen(
                        modifier = Modifier.fillMaxSize().background(color = Color.Cyan)
                    )
                },
                secondComposable = {
                    SecondScreen(
                        modifier = Modifier.fillMaxSize().background(color = Color.Red)
                    )
                }
            )
        }

        is ListHalfAndDetailHalf -> {
                AdaptiveLayoutListHalfAndDetailHalf(
                    firstComposable = {
                        FirstScreen(
                            modifier = Modifier.fillMaxSize().background(color = Color.Cyan)
                        )
                    },
                    secondComposable = {
                        SecondScreen(
                            modifier = Modifier.fillMaxSize().background(color = Color.Red)
                        )
                    }
                )
        }

        is ListAndDetailStacked -> {
            AdaptiveLayoutListAndDetailStacked(
                firstComposable = {
                    FirstScreen(modifier = Modifier.fillMaxSize().background(color = Color.Cyan))
                },
                secondComposable = {
                    SecondScreen(modifier = Modifier.fillMaxSize().background(color = Color.Red))
                }
            )
        }

        else -> {
            FirstScreen(modifier = Modifier.fillMaxSize().background(color = Color.Cyan))
        }
    }
}

@Composable
@Preview
fun FirstScreen(modifier: Modifier = Modifier) {
    Column(modifier) { }
}



@Composable
fun SecondScreen(modifier: Modifier = Modifier) {
    Column(modifier) { }
}