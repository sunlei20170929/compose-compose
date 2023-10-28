package com.example.compose_compose.ui.home.layouttab

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.compose_compose.ui.getAllComponents
import com.example.compose_compose.ui.getAllLayout
import com.example.compose_compose.ui.getAllLazyLayout
import com.microsoft.device.dualscreen.draganddrop.DragData
import com.microsoft.device.dualscreen.draganddrop.DragTarget
import com.microsoft.device.dualscreen.draganddrop.MimeType
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 *  modifier = Modifier
 *                 .size(400.dp)
 *                 .background(Color.Blue)
 *                 .pointerInput(Unit) {
 *                     awaitPointerEventScope {
 *
 *                     /////   PointerEventPass.Final/Main/Initial  /////
 *
 *                         awaitPointerEvent(PointerEventPass.Final)
 *                         Log.d("compose_study", "second layer")
 *                     }
 *                 }
 *
 * */


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun composableContainer(modifier:Modifier,type:Int = 1) {

    var items: SnapshotStateList<String>? = null
//    val dragData = DragData(type = MimeType.TEXT_PLAIN,data = "widget")
    when(type){
        1 ->  items = remember { getAllLayout().toMutableStateList() }
        2 ->  items = remember { getAllComponents().toMutableStateList() }
        3 ->  items = remember { getAllLazyLayout().toMutableStateList() }
        else -> items = remember { getAllLazyLayout().toMutableStateList() }
    }


    FlowRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {

//        DragTarget(dragData = dragData) {
            for(item in items){
                draggableChip(item.toString(),Modifier){
                    Log.e("sunlei","add an composable component")
                    items.add(item)
                }
            }
//        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun draggableChip(name:String, modifier: Modifier, addItem:()->Unit){

    var offset by remember { mutableStateOf(Offset.Zero) }
//    val scope = rememberCoroutineScope()
    val dragData = DragData(type = MimeType.TEXT_PLAIN,data = name)
    DragTarget(dragData = dragData) {
//        Box(
//            modifier = modifier.padding(top = 20.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Canvas(
//                modifier = Modifier.size(450.dp, 60.dp)
//            ) {
//                drawRoundRect(
//                    color = Color.Blue,
//                    cornerRadius = CornerRadius(20f),
//                    style = Stroke(
//                        width = 5f,
//                        pathEffect = PathEffect.dashPathEffect(
//                            intervals = floatArrayOf(20f, 10f),
//                            phase = 10f
//                        )
//                    )
//                )
//            }
//            Text(
//                text = name,
//                textAlign = TextAlign.Center,
////                style = MaterialTheme.typography.h5
//            )
//        }
        AssistChip(
            onClick = { null },
            label = { Text("$name") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "layout",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            },
//            modifier = Modifier
//                .offset {
//                    IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
//                }
//            .pointerInput(Unit) {
//                detectDragGestures(
//                    onDragStart = { },
//                    onDrag = { PointerInputChange, dragAmount ->
//                        offset += dragAmount
//                    },
//                    onDragCancel = { },
//                    onDragEnd = {
////                        addItem()
//                        scope.launch {
//                            addItem()
//                        }
//                        offset = Offset.Zero
//                    }
//                )
//
//            }
        )
    }
}
@Preview
@Composable
fun showSomeLayout(){
    composableContainer(modifier = Modifier,2)
}