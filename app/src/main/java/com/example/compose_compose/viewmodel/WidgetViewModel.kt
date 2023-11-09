package com.example.compose_compose.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

@HiltViewModel
class WidgetViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    val context = application.applicationContext

    val files = listOf<String>().toMutableList()

    suspend fun fileList(): Flow<List<String>>  = flow{
        Log.e("draw","context filelist is ${context.fileList().size}")
        if(context.fileList()!=null){
           context.fileList().forEach {
               files.add(it)
           }
        }

    }
}