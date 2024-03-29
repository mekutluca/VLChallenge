package dev.kutluca.vlchallenge.util.compose

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.launch

@Composable
fun Modifier.swipable(
    onSwipedLeft: () -> Unit,
    onSwipedRight: () -> Unit,
    rotationThreshold: Int = 10,
): Modifier {
    val offset = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    val width = LocalConfiguration.current.screenWidthDp

    return this then Modifier
        .draggable(
            state = rememberDraggableState {
                scope.launch {
                    offset.animateTo(offset.targetValue + it)
                }
            },
            orientation = Orientation.Horizontal,
            onDragStopped = {
                val rotation = (offset.value / width) * 10
                if (rotation > rotationThreshold) {
                    offset.animateTo(width.toFloat() * 5)
                    onSwipedRight()
                    offset.snapTo(0f)
                } else if (rotation < -rotationThreshold) {
                    offset.animateTo(-width.toFloat() * 5)
                    onSwipedLeft()
                    offset.snapTo(0f)
                } else {
                    offset.animateTo(0f)
                }
            }
        )
        .graphicsLayer(
            translationX = offset.value,
            rotationZ = (offset.value / width) * 10
        )
}