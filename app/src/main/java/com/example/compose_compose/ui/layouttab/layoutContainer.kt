package com.example.compose_compose.ui.layouttab

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


fun someLayoutItems():List<String>{
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
    FlowRow(modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        
        val items = someLayoutItems()
        for(item in items){
            AssistChip(
                onClick = {    /*TODO*/  },
                label = {Text("$item")},
                leadingIcon = { Icon(
                    Icons.Filled.Add,
                    contentDescription = "layout",
                    Modifier.size(AssistChipDefaults.IconSize)
                )}
            )
        }

    }
}

@Preview
@Composable
fun showSomeLayout(){
    ComposeLayoutContainer(modifier = Modifier)
}