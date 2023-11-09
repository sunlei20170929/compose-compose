package com.example.compose_compose.ui.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import javax.inject.Inject

@Composable
fun WidgetScreen(onBackPressed:()->Unit,
                 vm: ViewModel
){

    val data = listOf("widget1","widget2","widget3","widget4","widget5",)
    LazyColumn{
        items(data){
            Text(text=it,)

        }
    }
}