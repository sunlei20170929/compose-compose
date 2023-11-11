package com.example.compose_compose.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val repository:CCRepository
    application:Application
): AndroidViewModel(application){

    private val _tree : TreeNode = TreeNode("root",null)
    val tree: TreeNode = _tree

    fun saveTreetoJSON( filename:String){
        viewModelScope.launch(Dispatchers.IO){
            val gson = Gson()
            val savetree = gson.toJson(_tree)
Log.w("draw","json tree is $savetree")
            getApplication<Application>().applicationContext.openFileOutput(filename, Context.MODE_PRIVATE).use {
                it.write(savetree.toByteArray())
            }
        }
    }
}

class TreeNode constructor(val name:String,val parent:String?){

    //    private val root: TreeNode = TreeNode("root")
    var _children:MutableList<TreeNode> = listOf<TreeNode>().toMutableList()

    fun addChild(childName:String){
        _children.add(TreeNode(childName,null))
    }
    fun addSubChild(parent:TreeNode,child:String){
        parent._children.add(TreeNode(child, parent = parent.name))
    }

//    var children:List<TreeNode> = _children

    fun addChild(parent:TreeNode,childname:String): TreeNode? {
        return parent.apply {
            _children?.add(TreeNode(childname,this.name))
        }
    }

    fun clearTree(node:TreeNode){
        node._children.clear()
//        node._children = mutableListOf<TreeNode>()
    }

    fun hasFather(node:TreeNode):Boolean{
        return true
    }

    fun removeChild(childname:String){
        _children.remove(TreeNode(childname,null))
    }

    fun getChildren(parent:TreeNode):List<TreeNode>?{
        return parent._children
    }

    fun deleteChild(parent:TreeNode,child:TreeNode){
        parent._children.remove(child)
    }
}