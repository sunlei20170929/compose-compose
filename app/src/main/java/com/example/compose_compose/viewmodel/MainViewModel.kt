package com.example.compose_compose.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val repository:CCRepository
): ViewModel(){

    companion object{
        private val _tree : TreeNode = TreeNode("root", mutableListOf<TreeNode>())
    }
    val tree: TreeNode = _tree
    fun addChild(child:String){
        Log.e("draw","add $child")
        _tree.addChild(_tree,child)
    }

    fun childrenCount(): List<TreeNode>? {
        return _tree.getChildren(_tree)
    }


}

class TreeNode constructor(val name:String, private val children:MutableList<TreeNode>){

//    private val root: TreeNode = TreeNode("root")

    fun addChild(parent:TreeNode,childname:String):TreeNode{
       return parent.apply {
           this.children?.add(TreeNode(childname, listOf<TreeNode>().toMutableList()))
       }
    }

    fun getChildren(parent:TreeNode):List<TreeNode>?{
        return children
    }

    fun deleteChild(parent:TreeNode,child:TreeNode){
        parent.children.remove(child)
    }
}