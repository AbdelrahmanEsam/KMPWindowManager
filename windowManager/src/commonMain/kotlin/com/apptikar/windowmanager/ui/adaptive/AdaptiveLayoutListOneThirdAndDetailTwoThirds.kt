import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AdaptiveLayoutListOneThirdAndDetailTwoThirds(
    firstComposable : @Composable () -> Unit,
    secondComposable : @Composable () -> Unit
) {

    Row {

        Box(modifier = Modifier.fillMaxWidth(0.3f)) {
            firstComposable()
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            secondComposable()
        }
    }
}