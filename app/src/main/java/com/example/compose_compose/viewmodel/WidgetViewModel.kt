package com.example.compose_compose.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class WidgetViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    val context = application.applicationContext

    private val _files = MutableStateFlow<List<String>>(emptyList())

    val files: StateFlow<List<String>> = _files.asStateFlow()

    init{
         getFileList()
    }
    private fun getFileList(){
        viewModelScope.launch {
            val files = listOf<String>().toMutableList()
            if(context.fileList()!=null){
                context.fileList().forEach {
                    files.add(it)
                }
            }
            _files.value = files
        }

    }

    fun delete(name:String){
        val file = File(name)
        file.delete()
    }

}