package com.example.compose_compose.ui.widget

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import com.example.compose_compose.viewmodel.WidgetViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@Composable
fun WidgetScreen(onBackPressed:()->Unit,
                 vm: WidgetViewModel
){

    val source by vm.files.collectAsState()

    LazyColumn{
        items(source){
            Log.w("draw","source item is $it")
            Text(text=it,modifier = Modifier.padding(16.dp))
        }
    }
}


