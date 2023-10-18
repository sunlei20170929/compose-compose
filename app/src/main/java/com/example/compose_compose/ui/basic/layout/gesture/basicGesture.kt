package com.example.compose_compose.ui.basic.layout.gesture

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun basicClickTap(modifier: Modifier){
    val count = remember { mutableStateOf(0) }
    val modifier = Modifier.clickable { count.value+=1 }.apply {
        pointerInput(Unit){
            detectTapGestures(
                onPress = {},
                onDoubleTap = {},
                onLongPress = {},
                onTap = {}
            )
        }
    }
}

@Composable
fun basicScroll(modifier:Modifier){
//    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .background(Color.LightGray)
        .size(100.dp)
        .verticalScroll(rememberScrollState())){
        repeat(10){
            Text("TEXT ITEM $it",modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun testScrollable(modifier:Modifier){
    var offset by remember { mutableFloatStateOf(0f) }
    Box(
        Modifier
            .size(150.dp)
            .background(Color.LightGray)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { it ->
                    offset += it
                    it
                }
            )) {
        Text(offset.toString())

    }
}

@Composable
fun nestScroll(modifier:Modifier){
    val gradient = Brush.verticalGradient(0f to Color.Gray,1000f to Color.White)

    Box(
        Modifier
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ){
        Column {
            repeat(6){
                Box(modifier= Modifier
                    .height(128.dp)
                    .verticalScroll(rememberScrollState())){
                    Text("Scroll here $it",modifier= Modifier
                        .border(12.dp, Color.DarkGray)
                        .background(brush = gradient)
                        .padding(24.dp)
                        .height(150.dp))
                }
            }
        }
                
    }
}

@Composable
fun dragSomething(modifier:Modifier){
//    var offsetX by remember { mutableFloatStateOf(0f) }
//    val brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
//    Text(modifier=Modifier.offset{ IntOffset(offsetX.roundToInt(),0) }
//        .draggable(orientation = Orientation.Horizontal,state = rememberDraggableState(onDelta = {delta->
//            offsetX+=delta
//        })).border(width = 1.dp, shape = RectangleShape, brush = brush).size(200.dp),
//        text = "Drag me!",)

    Box(modifier = Modifier.fillMaxSize()){
        var offsetX by remember { mutableFloatStateOf(0f) }
        var offsetY by remember { mutableFloatStateOf(0f) }

        Box(modifier = Modifier.offset{ IntOffset(offsetX.roundToInt(), offsetY.roundToInt())}
            .background(Color.Cyan).size(100.dp).pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX+=dragAmount.x
                    offsetY+=dragAmount.y

                }
            })
    }
}


@Preview
@Composable
fun showGesture(){
    basicClickTap(modifier = Modifier)
//    basicScroll(modifier = Modifier)
//    testScrollable(modifier = Modifier)
//    nestScroll(modifier = Modifier)
    dragSomething(modifier = Modifier)
}