package com.lyh.spintest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.PersistentList

@Stable
data class SpinWheelItem(
    val colors: PersistentList<Color>,
    val content: @Composable () -> Unit,
)


internal fun List<SpinWheelItem>.getDegreesPerItem(): Float =  360f / this.size.toFloat()