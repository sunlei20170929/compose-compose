package com.example.compose_compose.ui.basic.layout.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun demoTabRow(modifier: Modifier){
    val tabs = listOf("Tab 1","Tab 2","Tab 3")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTabIndex,
//        modifier = Modifier.focuRestorer()
    ){

    }
}