import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AdaptiveLayoutListHalfAndDetailHalf(
    hingeRatio: Float = 0.5f,
    firstComposable: @Composable () -> Unit,
    secondComposable: @Composable () -> Unit,
) {

    Row(Modifier.fillMaxSize().background(color = Color.Blue)){
        Box(
            modifier = Modifier.fillMaxWidth(
                hingeRatio
            )
        ) {
            firstComposable()
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            secondComposable()
        }
    }


}