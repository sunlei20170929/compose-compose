package com.example.compose_compose.ui.basic.layout.animate

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun expandBox(modifier: Modifier){

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .background(Color.Cyan)
        .animateContentSize { initialValue, targetValue ->

        }
        .height(if (expanded) 400.dp else 200.dp)
        .height(200.dp)
        .fillMaxWidth()
        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
            expanded = !expanded
        }){

    }
}

@Composable
fun movedBox(modifier: Modifier){
    var moved by remember { mutableStateOf(false) }
    val pxToMove = with(LocalDensity.current){
        200.dp.toPx().roundToInt()
    }
    val offset by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset(pxToMove,pxToMove)
        }else{
            IntOffset.Zero
        }, label = "animate offset"
    )

    Box(modifier = Modifier
        .offset { offset }
        .background(Color.Blue)
        .size(100.dp)
        .clickable(interactionSource = remember{ MutableInteractionSource()},indication=null){
            moved = !moved
        })
}


@Preview
@Composable
fun previewExpandableBox(){
//    expandBox(modifier = Modifier)
    movedBox(modifier = Modifier)
}