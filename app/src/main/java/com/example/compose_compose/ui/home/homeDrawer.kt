package com.example.compose_compose.ui.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeDrawer(modifier: Modifier){
    Text("Drawer title",style = androidx.compose.material.MaterialTheme.typography.subtitle1, modifier = Modifier.padding(16.dp))
    Spacer(Modifier.height(24.dp))
    NavigationDrawerItem(
        label = { Text(text = "My Widget") },
        selected = false,
        onClick = {  }
    )
    Spacer(Modifier.height(24.dp))
    Text("About",style = androidx.compose.material.MaterialTheme.typography.h4)

}

@Preview
@Composable
fun previewHomeDrawer(){
    homeDrawer(modifier = Modifier)
}