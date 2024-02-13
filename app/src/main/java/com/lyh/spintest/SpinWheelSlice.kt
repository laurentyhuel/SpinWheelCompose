package com.lyh.spintest

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.lyh.spintest.util.toColor

@Composable
internal fun SpinWheelSlice(
    modifier: Modifier = Modifier,
    size: Dp,
    brush: Brush,
    degree: Float,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size)
    ) {
        Canvas(
            modifier = Modifier
                .size(size)
        ) {

            drawArc(
                brush = brush,
                startAngle = -90f - (degree / 2),
                sweepAngle = degree,
                useCenter = true,
            )
        }
        Box(modifier = Modifier.align(Alignment.TopCenter).padding(top = 20.dp)) {
            content()
        }
    }
}

@Preview
@Composable
private fun OneSpinWheelSlice2Preview() {
    Box(
        modifier = Modifier
            .size(200.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {

            drawArc(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Red,
                        Color.Yellow
                    ),
                    endY = 200.dp.toPx() /2f

                ),
                startAngle = -90f - (30f / 2),
                sweepAngle = 30f,
                useCenter = true,
            )
        }
    }
}

@Preview
@Composable
private fun OneSpinWheelSlicePreview() {
    BoxWithConstraints(modifier = Modifier.size(400.dp)) {
        val degree = 360f / 10.toFloat()
        val size = min(this.maxHeight, this.maxWidth)
        val brushEnd = with(LocalDensity.current) { size.toPx() / 2f }
        SpinWheelSlice(
            size = size,
            brush = Brush.verticalGradient(
                 listOf(
                    Color.Red,
                    Color.Yellow
                ),
                startY = 0f,
                endY = brushEnd,
            ),
            degree = degree,
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
            ) {
                Text(
                    text = "20$",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xFF4CAF50),
                    fontSize = 20.sp,

                    )
            }
        }
    }
}

@Preview
@Composable
private fun FullSpinWheelSlicePreview() {

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

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val degree = 360f / 6.toFloat()
        val size = min(this.maxHeight, this.maxWidth)
        val brushEnd = with(LocalDensity.current) { size.toPx() / 2f }

        repeat(6) { index ->
            SpinWheelSlice(
                modifier = Modifier.rotate(degrees = degree * index),
                size = size,
                brush = Brush.verticalGradient(
                    if (index % 2 == 0) colors1 else colors2,
                    endY = brushEnd,
                ),
                degree = degree,
            ){

                val modifier = if (index == 3)
                    Modifier
                        .padding(2.dp)
                        .border(2.dp, Color.White, RoundedCornerShape(10.dp))
                        .background(
                            brush = Brush.verticalGradient(listOf(Color(0xFFFFF54C), Color(0xFFFFF109))),
                            shape = RoundedCornerShape(10.dp),
                        )
                else Modifier

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .then(modifier)
                ) {
                    Text(
                        text = "${20+index}$",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFF4CAF50),
                        fontSize = 20.sp,

                    )
                }
            }
        }
    }
}


