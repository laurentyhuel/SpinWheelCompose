package com.lyh.spintest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.lyh.spintest.util.toColor
import kotlinx.collections.immutable.toPersistentList

@Composable
internal fun SpinWheelComponent(spinWheelState: SpinWheelState) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.769f)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = spinWheelState.backgroundImage),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.769f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(27f))
            Image(
                modifier = Modifier
                    .weight(12f)
                    .aspectRatio(1f),
                painter = painterResource(id = spinWheelState.indicatorImage),
                contentDescription = null
            )
            BoxWithConstraints(
                modifier = Modifier
                    .weight(82f)
                    .aspectRatio(1f)
            ) {
                val imageSize = this.maxHeight.times(0.14f)
                SpinWheel(modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationZ = spinWheelState.rotation.value
                    }, items = spinWheelState.items)
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(imageSize),
                    painter = painterResource(id = spinWheelState.centerImage),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.weight(9f))
        }
    }
}


@Preview
@Composable
private fun SpinWheelComponentPreview() {
    Box(modifier = Modifier.fillMaxSize()) {

        val colors1 = remember {
            listOf(
                "380048",
                "2B003D",
                "40004A",
                "590058",
                "730067"
            ).map { it.toColor() }
        }

        val colors2 = remember {
            listOf(
                "F9A114",
                "FD7D1B",
                "F9901A",
                "F6A019",
                "EFC017"
            ).map { it.toColor() }
        }

        val items = remember {
            List(6) { index ->
                val colors = if (index % 2 == 0) colors1 else colors2

                SpinWheelItem(
                    colors = colors.toPersistentList()
                ) {
                    Text(text = "$$index")
                }

            }.toPersistentList()
        }

        val state = rememberSpinWheelState(
            items = items,
            backgroundImage = R.drawable.spin_wheel_background,
            centerImage = R.drawable.spin_wheel_center,
            indicatorImage = R.drawable.spin_wheel_tick,
            onSpinningFinished = {},
        )
        SpinWheelComponent(state)
    }
}