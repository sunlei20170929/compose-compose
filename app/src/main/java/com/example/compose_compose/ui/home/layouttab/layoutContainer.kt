package com.example.compose_compose.ui.home.layouttab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_compose.ui.getAllComponents
import com.example.compose_compose.ui.getAllLayout
import com.example.compose_compose.ui.getAllLazyLayout
import com.microsoft.device.dualscreen.draganddrop.DragData
import com.microsoft.device.dualscreen.draganddrop.DragTarget
import com.microsoft.device.dualscreen.draganddrop.MimeType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun composableContainer(modifier:Modifier,type:Int = 1) {

    var items: SnapshotStateList<String>? = null

    items = when(type){
        1 -> remember { getAllLayout().toMutableStateList() }
        2 -> remember { getAllComponents().toMutableStateList() }
        3 -> remember { getAllLazyLayout().toMutableStateList() }
        else -> remember { getAllLazyLayout().toMutableStateList() }
    }

    FlowRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for(item in items){
                draggableChip(item)
            }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun draggableChip(name: String){

    val dragData = DragData(type = MimeType.TEXT_PLAIN,data = name)

    DragTarget(dragData = dragData) {
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
        )
    }
}
@Preview
@Composable
fun showSomeLayout(){
    composableContainer(modifier = Modifier,2)
}