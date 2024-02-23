package com.lyh.spintest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.lyh.spintest.ui.theme.SpinTestTheme
import com.lyh.spintest.util.toColor
import kotlinx.collections.immutable.toPersistentList
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpinTestTheme {
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
                    List(8) { index ->
                        val colors = if (index % 2 == 0) colors1 else colors2

                        SpinWheelItem(
                            colors = colors.toPersistentList()
                        ) {
                            Text(
                                text = "$$index",
                                style = TextStyle(color = Color(0xFF4CAF50), fontSize = 20.sp)
                            )
                        }

                    }.toPersistentList()
                }
                var pickerValue by remember { mutableIntStateOf(0) }

                val spinState = rememberSpinWheelState(
                    items = items,
                    backgroundImage = R.drawable.spin_wheel_background,
                    centerImage = R.drawable.spin_wheel_center,
                    indicatorImage = R.drawable.spin_wheel_tick,
                    onSpinningFinished = null,
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp)
                ) {


                    Box(modifier = Modifier.size(300.dp)) {
                        SpinWheelComponent(spinState)
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Button(onClick = {
                        spinState.goto(pickerValue)
                    }) {
                        Text(text = "Goto")
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Button(onClick = {
                        spinState.launchInfinite()
                    }
                    ) {
                        Text(text = "Infinite")
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Button(onClick = {
                        spinState.stoppingWheel(pickerValue)
                    }) {
                        Text(text = "Stop")
                    }
                    Spacer(modifier = Modifier.size(20.dp))




                    NumberPicker(
                        value = pickerValue,
                        range = items.indices,
                        onValueChange = {
                            pickerValue = it
                        }
                    )
                }

            }
        }
    }
}

fun getDegreeFromSection(items: List<SpinWheelItem>, section: Int): Float {
    val pieDegree = 360f / items.size
    return pieDegree * section.times(-1)
}

fun getDegreeFromSectionWithRandom(items: List<SpinWheelItem>, section: Int): Float {
    val pieDegree = 360f / items.size
    val exactDegree = pieDegree * section.times(-1)

    val pieReduced = pieDegree * 0.9f //to avoid stop near border
    val multiplier = if (Random.nextBoolean()) 1f else -1f //before or after exact degree
    val randomDegrees = Random.nextDouble(0.0, pieReduced / 2.0)
    return exactDegree + (randomDegrees.toFloat() * multiplier)
}