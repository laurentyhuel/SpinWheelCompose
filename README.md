# A spin wheel or wheel of fortune in Compose

A spin-wheel for Android in full Jetpack Compose.

# How to use it ?
## Setup
    val colors1 = remember {  
        listOf(Color.Red, Color.Yellow)
    }  
    val colors2 = remember {  
        listOf(Color.Yellow, Color.Red)
    }  
      
    val items = remember {  
	    List(8) { index ->  
		    val colors = if (index % 2 == 0) colors1 else colors2  
		      
		    SpinWheelItem(colors = colors) {  
			    Text(  
			        text = "$$index",  
			        style = TextStyle(color = Color(0xFF4CAF50), fontSize = 20.sp)  
			    )  
		    }  
	    }  
    }
    val spinState = rememberSpinWheelState(  
        items = items,  
        backgroundImage = R.drawable.spin_wheel_background,  
        centerImage = R.drawable.spin_wheel_center,  
        indicatorImage = R.drawable.spin_wheel_tick,  
        onSpinningFinished = null,  
    )
    Box(modifier = Modifier.size(300.dp)) {  
        SpinWheelComponent(spinState)  
    }

## Play with wheel
- Go a specific section  
  ```spinState.goto(value)```

- rotate indefinitely  
  ```spinState.launchInfinite()```

- stop smoothly at a specific section
  ```spinState.stoppingWheel(value) ```
# Demo

![demo](demo/demo.GIF)

