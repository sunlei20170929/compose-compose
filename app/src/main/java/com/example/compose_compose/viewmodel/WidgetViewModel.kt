package com.example.compose_compose.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.Stack
import javax.inject.Inject

@HiltViewModel
class WidgetViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    val context: Context = application.applicationContext
    private val travelStack = Stack<String>()
    private val codeStringBuilder = StringBuilder()

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
        context.fileList().forEach {
            if(it.equals(name)){
                context.deleteFile(name)
            }
        }
        getFileList()
    }

    fun stackPush(nodename:String){
        travelStack.push(nodename)
    }

    fun stackPop():String{
        return if(!travelStack.empty()){
            return travelStack.pop()
//            return "}"
        }
        else
            "EOS" //end of stack
    }

    fun addNode(node:String){
        codeStringBuilder.append("$node(){\n")
    }

    fun addBrace(){
        codeStringBuilder.append("\n"+"}"+"\n")
    }

    fun openFiletoJSON(filename: String): JSONObject {
        context.openFileInput(filename).bufferedReader().useLines {
            codeStringBuilder.append(it)
        }
        return JSONObject(codeStringBuilder.toString())
    }

    fun fromJsonToTree(json:JSONObject):TreeNode{
        val gson = Gson()
        return gson.fromJson(json.toString(),TreeNode::class.java)
    }

    /**
     * travel the tree to generate code
     * */
    fun travelTree(tree:TreeNode){

    }

}