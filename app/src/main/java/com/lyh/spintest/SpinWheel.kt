package com.lyh.spintest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.lyh.spintest.util.toBrush
import com.lyh.spintest.util.toColor

@Composable
internal fun SpinWheel(
    modifier: Modifier = Modifier,
    items: List<SpinWheelItem>,
) {
    BoxWithConstraints(modifier = modifier) {

        val degreesPerItems = items.getDegreesPerItem()
        val size = min(this.maxHeight, this.maxWidth)
        val brushEnd = with(LocalDensity.current) { size.toPx() / 2f }

        items.forEachIndexed { index, item ->
            SpinWheelSlice(
                modifier = Modifier.rotate(degrees = degreesPerItems * index),
                size = size,
                brush = item.colors.toBrush(brushEnd),
                degree = degreesPerItems,
                content = item.content
            )
        }
    }
}


@Preview
@Composable
private fun SpinWheelPreview(
) {
    val colors1 = listOf(
        "380048",
        "2B003D",
        "40004A",
        "590058",
        "730067"
    ).map { it.toColor() }

    val colors2 = listOf(
        "F9A114",
        "FD7D1B",
        "F9901A",
        "F6A019",
        "EFC017"
    ).map { it.toColor() }

    Box(modifier = Modifier.size(300.dp)) {
        SpinWheel(
            modifier = Modifier.fillMaxSize(),
            items = List(6) { index ->
                val colors = if (index % 2 == 0) colors1 else colors2

                SpinWheelItem(
                    colors = colors
                ) {
                    Text(text = "$$index")
                }
            },
        )
    }
}