# KMPWindowManager

<div align="center">
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</div>

### KMPWindowManager  provides a unified API that allows you to support different screen sizes and orientations and more.

<hr>


KMPWindowManager is available on mavenCentral().


## Install

```kotlin
implementation("io.github.abdelrahmanesam:KMPWindowManager:${latest-version}")
```



### How to use it

#### First in `iosMain` 

```kotlin
// use WindowManagerViewControllerWrapper to start observing your ios changes
fun MainViewController(): WindowManagerViewControllerWrapper {
    return App()
}
```

#### Second in `androidMain`

```kotlin
// add observeScreenChanges in your onCreate 
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    observeScreenChanges()
}

// clearScreenObserver in onDestroy
override fun onDestroy() {
    super.onDestroy()
    clearScreenObserver()
}
```

#### In `commonMain`

```kotlin
@Composable
fun RootComposable(modifier: Modifier = Modifier) {

    //start collecting your screenData and change it to adaptiveUIType
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
```

### you can also change statusBar color 
```kotlin
ChangeStatusBarColor(color = Color.Red)
```


### and navigationBar color in android / homeIndicator in ios
```kotlin
//note that due to limitations in ios you need to change your 
// statusBarColor too if you need to change your home indicator color
// or your statusBarColor will be the same as your home indicator color
ChangeBottomNavigationColor(color = Color.Red)
```
