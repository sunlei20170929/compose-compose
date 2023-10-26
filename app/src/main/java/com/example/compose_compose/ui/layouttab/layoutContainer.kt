package com.example.compose_compose.ui.layouttab

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
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
fun someLayoutItems(): MutableList<String> {

    val arr = ArrayList<String>()
    //pager
    arr.add("HorizontalPager")
    arr.add("VerticalPager")
    //flowlayout
    arr.add("FlowRow")
    arr.add("FlowColumn")

    arr.add("Column")
    arr.add("Row")
    arr.add("Box")
    arr.add("Scaffold")
    return arr

}
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ComposeLayoutContainer(modifier:Modifier) {

    var items = remember { someLayoutItems().toMutableStateList() }

    FlowRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        for(item in items){
            draggableChip(item.toString(),Modifier){
                Log.e("sunlei","add an composable component")
                items.add(item)
            }
        }

    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun draggableChip(name:String, modifier: Modifier, addItem:()->Unit){

    var offset by remember { mutableStateOf(Offset.Zero) }
    val scope = rememberCoroutineScope()

    AssistChip(
        onClick = {    /*TODO*/  },
        label = {Text("$name")},
        leadingIcon = { Icon(
            Icons.Filled.Add,
            contentDescription = "layout",
            Modifier.size(AssistChipDefaults.IconSize)
        )},
        modifier = Modifier
            .offset {
                IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {

                    },
                    onDrag = { PointerInputChange, dragAmount ->
                        offset += dragAmount
                    },
                    onDragCancel = {

                    },
                    onDragEnd = {
//                        addItem()
                        scope.launch {
                            addItem()
                        }
                        offset = Offset.Zero
                    }
                )

            }
    )
}
@Preview
@Composable
fun showSomeLayout(){
    ComposeLayoutContainer(modifier = Modifier)
}