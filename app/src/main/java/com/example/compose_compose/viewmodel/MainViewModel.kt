package com.example.compose_compose.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val repository:CCRepository
): ViewModel(){

    private val _tree : TreeNode = TreeNode("root",null)
    val tree: TreeNode = _tree

    fun saveTreetoJSON(){
       val gson = Gson()
        val savetree = gson.toJson(_tree)
        Log.w("draw","saved json string is $savetree")
    }


}

class TreeNode constructor(val name:String,val parent:String?){

//    private val root: TreeNode = TreeNode("root")
    private var _children:MutableList<TreeNode> = listOf<TreeNode>().toMutableList()

    fun addChild(childName:String){
        _children.add(TreeNode(childName,null))
    }
    fun addSubChild(parent:TreeNode,child:String){
        parent._children.add(TreeNode(child, parent = parent.name))
    }

    var children:List<TreeNode> = _children

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
        return parent.children
    }

    fun deleteChild(parent:TreeNode,child:TreeNode){
        parent._children.remove(child)
    }
}