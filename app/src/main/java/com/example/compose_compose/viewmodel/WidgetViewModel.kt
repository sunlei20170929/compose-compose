package com.example.compose_compose.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
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
        codeStringBuilder.clear()
        travelStack.clear()
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

    private fun stackPush(nodename:String){
        travelStack.push(nodename)
    }

    private fun stackPop():String{
        return if(!travelStack.empty()){
            travelStack.pop()
            return "\n"+"}"
        }
        else "END"
//            "\n"+"}"+"\n" //end of stack
    }

    private fun addNode(node:String){
        codeStringBuilder.append("\n$node(){\n")
    }
    private fun addNodeWithEnd(node:String){
        codeStringBuilder.append("\n$node(){\n}")
    }

    private fun addEndBrace(){
        codeStringBuilder.append("\n"+"}"+"\n")
    }

    private fun openFiletoJSON(filename: String): JSONObject {
        context.openFileInput(filename).bufferedReader().useLines {line->
            line.forEach{
                codeStringBuilder.append(it)
            }
        }
        val fileContent = codeStringBuilder.toString()
        Log.w("draw","saved string is $fileContent")
        codeStringBuilder.clear()
        return JSONObject(fileContent)
    }

    private fun fromJsonToTree(json:JSONObject):TreeNode{
        val gson = Gson()
        return gson.fromJson(json.toString(),TreeNode::class.java)
    }

    /**
     * travel the tree to generate code
     * */
    private fun travelTree(tree:TreeNode){
        if(tree._children.size>0){
            val nodes = tree._children
            for(node in nodes){
                if(node._children.isEmpty()){
                    addNodeWithEnd(node.name)
                }else{
                    stackPush(node.name)
                    addNode(node.name)
                    travelTree(node)
                }
            }
        }
    }

    var result = ""
    fun generateTreeCode(filename:String):String{
        val job = viewModelScope.launch {
//            withContext(Dispatchers.Default){

            //clear all temp container
            codeStringBuilder.clear()
            travelStack.clear()

            //read data
            val tree = fromJsonToTree(openFiletoJSON(filename))

            //travel
            travelTree(tree)
            while(travelStack.isNotEmpty()){
                codeStringBuilder.append(stackPop())
            }
            val code = codeStringBuilder.toString()
            Log.e("draw","code:\n$code")
            result = code

//            }
        }


        return result

    }

}