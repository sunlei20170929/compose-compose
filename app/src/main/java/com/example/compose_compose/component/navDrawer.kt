package com.example.compose_compose.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun navDrawer(modifier: Modifier){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
        ModalDrawerSheet {
            Text("Drawer Title", modifier = Modifier.padding(16.dp))
            Divider()
            NavigationDrawerItem(label = { Text("Drawer Item") }, selected = false, onClick = { /*TODO*/ })
            NavigationDrawerItem(label = { Text("Drawer Item2") }, selected = false, onClick = { /*TODO*/ })
            NavigationDrawerItem(label = { Text("Drawer Item3") }, selected = false, onClick = { /*TODO*/ })

        }
    },
        gesturesEnabled = true,
        ) {
        //screen content
        myScaffold(modifier)
    }
}