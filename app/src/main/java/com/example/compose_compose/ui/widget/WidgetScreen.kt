package com.example.compose_compose.ui.widget

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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import com.example.compose_compose.viewmodel.WidgetViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Composable
fun WidgetScreen(onBackPressed:()->Unit,
                 vm: WidgetViewModel
){
    var data: Flow<List<String>>? = null

//    LaunchedEffect(key1 = Unit) {
//        data = vm.fileList()
//    }

    val source by remember  { mutableStateOf(data?.collectAsState()) }

    LazyColumn{
        items(source.value){
            Text(text=it,)

        }
    }
}


