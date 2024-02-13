package com.lyh.spintest

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class SpinWheelItem(
    val colors: List<Color>,
    val content: @Composable () -> Unit,
)


internal fun List<SpinWheelItem>.getDegreesPerItem(): Float =  360f / this.size.toFloat()