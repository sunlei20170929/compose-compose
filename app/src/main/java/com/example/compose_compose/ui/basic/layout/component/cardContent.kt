package com.example.compose_compose.ui.basic.layout.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardContent(modifier: Modifier){
    //a Card wraps its content in a Column composable
//    Card(
//    ElevatedCard(
    OutlinedCard(
        border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
//        elevation = CardDefaults.cardElevation(defaultElevation = 60.dp),
        modifier = Modifier.size(width=240.dp,height = 120.dp)
    ){
//        Text(text="card content",
//            modifier = Modifier.padding(16.dp),
//            textAlign = TextAlign.End)

        //assist chip demo
//        AssistChip(onClick = {}, label = { Text("AssistChip") },
//            leadingIcon = {
//                Icon(Icons.Filled.Settings,
//                    contentDescription="assist chip")
//            },
//            modifier = Modifier.padding(16.dp))

        //filter chip
//        var selected by remember { mutableStateOf(true) }
//        FilterChip(
//            modifier = Modifier.padding(16.dp),
//            onClick = {selected=!selected},
//            label = {Text("Filter Chip")},
//            selected = selected,
//            leadingIcon = {
//                if(selected){
//                    Icon(
//                        imageVector = Icons.Filled.Done,
//                        contentDescription = "Done icon",
//                        modifier = Modifier.size(FilterChipDefaults.IconSize)
//                    )
//                }else{
//                    null
//                }
//            }
//        )

        //input chip
        var enabled by remember { mutableStateOf(true) }

        if(!enabled) return@OutlinedCard
        InputChip(
            onClick={},
            label = {Text("input chip")},
            selected = enabled,
            avatar = {
                     Icon(Icons.Filled.Person,
                         contentDescription = "input chip description",
                         Modifier.size(InputChipDefaults.AvatarSize))

            },
            trailingIcon = {
                Icon(Icons.Default.Close,
                    contentDescription = "trailing icon description",
                    Modifier.size(InputChipDefaults.AvatarSize))
            }

            )
    }
}

@Preview
@Composable
fun previewCard(){
    CardContent(Modifier)
}