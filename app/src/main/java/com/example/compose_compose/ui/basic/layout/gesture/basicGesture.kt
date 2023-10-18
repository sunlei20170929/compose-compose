package com.example.compose_compose.ui.basic.layout.gesture

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
fun showGesture(){
    basicClickTap(modifier = Modifier)
}