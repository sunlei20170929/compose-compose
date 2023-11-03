package com.example.compose_compose.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_compose.viewmodel.MainViewModel
import com.example.compose_compose.viewmodel.TreeNode
import com.microsoft.device.dualscreen.draganddrop.DropContainer
import com.microsoft.device.dualscreen.draganddrop.MimeType

@Composable
fun cardResult(modifier: Modifier){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Text(
            text = "Drag component below:",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )

        dropContent(modifier)
    }
}

//var LocalisInChild = compositionLocalOf { false }
@Composable
fun dropContent(modifier: Modifier){

    var dragText by remember { mutableStateOf<String?>(null) }
    var dragImage by remember{ mutableStateOf<Painter?>(null) }

    val updateDragText:(String?)->Unit = {str->
        dragText = str
    }

    val updateDragImage:(Painter?)->Unit = {img->
        dragImage = img
    }

    var isDroppingItem by remember { mutableStateOf(false) }
    var isItemInBounds by remember { mutableStateOf(false) }

    val viewModel = hiltViewModel<MainViewModel>()
    val tree = rememberUpdatedState(newValue = viewModel.tree)

        DropContainer(modifier = modifier, onDrag = { inBounds, isDragging ->
            isDroppingItem = isDragging
            isItemInBounds = inBounds
        }) { dragData ->
            val boxColor = if (isDroppingItem && isItemInBounds)
                MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.surface
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = boxColor)
            )
            {
                dragData?.let {
                    if (it.type == MimeType.TEXT_PLAIN) {
                        dragText = dragData.data as String

                    }
                    if (it.type == MimeType.IMAGE_JPEG) {
                        dragImage = dragData.data as Painter
                    }
                }

                if (!isDroppingItem && isItemInBounds && tree.value.children.isEmpty()) {
                    LaunchedEffect(key1 = tree) {
                        Log.e("draw","root add $dragText")
                        tree.value.addChild(dragText.toString())
                    }
                }
            }
            DrawTree(modifier, nodes = tree.value)
            CloseButton(updateDragText, updateDragImage)
        }


}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DropPaneContent(dragText: String?, dragImage: Painter?) {
    Log.e("draw","DropPaneContent...DropPaneContent...DropPaneContent...")

    Column {
        if (dragText != null) {
            Text(
                text = dragText,
                textAlign = TextAlign.Center,
            )
        } else if (dragImage != null) {
            Image(
                painter = dragImage,
                contentDescription = "",
            )
        } else {
            Text(
                text = "default text",
                textAlign = TextAlign.Center,
            )
        }
    }
}
@Composable
fun DrawTree(modifier:Modifier,nodes: TreeNode){

    var dragText by remember { mutableStateOf<String?>(null) }
    var isDroppingItem by remember { mutableStateOf(false) }
    var isItemInBounds by remember { mutableStateOf(false) }

        val boxColor = if(isDroppingItem && isItemInBounds)
            MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.surface

//    if(nodes.children.isEmpty()) return
    Column() {
    for (node in nodes.children) {
        DropContainer(modifier = modifier, onDrag = {inBounds,isDragging->
            isDroppingItem = isDragging
            isItemInBounds = inBounds

        }) {dragData->
                Column(modifier = Modifier
                    .background(color = Color.Cyan)
                    .fillMaxWidth()
                ) {
                    Text(text = node.name)
//                    Text(text="tree node")
                    dragData?.let {
                        if (it.type == MimeType.TEXT_PLAIN) {
                            dragText = dragData.data as String

                            val subtree = rememberUpdatedState(newValue = nodes)
                            if(isItemInBounds && !isDroppingItem){
                                Log.e("draw","add node name is ${node.name}")
                                Log.w("draw","subtree is ${subtree.value.name}")
                                LaunchedEffect(key1 = nodes) {
//                                    if(node.children.isEmpty())
//                                    if(subtree.value.name.equals(node.parent) || subtree.value.name.equals("root"))
                                        node.addSubChild(node,dragText.toString())
//                                    else{
//                                        只在最低层添加
//                                    }

                                }
                            }
                        }
                    }

                }
            }
        Row(modifier.padding(horizontal = 20.dp)){
            DrawTree(modifier=Modifier.background(color = boxColor), nodes = node)
        }
        }

    }
}
@Preview
@Composable
fun previewResult(){
//    val subsubnodes = listOf(TreeNode("sub-sub-child1"),TreeNode("sub-sub-child2"))
//    val subnodes = listOf(TreeNode("sub-child1"),TreeNode("sub-child2"))
//    val root:TreeNode = TreeNode("root").apply {
//        children = listOf(TreeNode("child1"),TreeNode("child2"))
//        children[0].children = subnodes
////        children[1].children = subnodes
////        children[0].children[0].children = subsubnodes
//        children[0].children[1].children = subsubnodes
//    }

//    DrawTree(Modifier,nodes = root)
}

// to reset drag and drop
@Composable
fun CloseButton(
    updateDragText: (String?) -> Unit,
    updateDragImage: (Painter?) -> Unit
) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = {
                updateDragText(null)
                updateDragImage(null)
            }
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                imageVector = Icons.Filled.Close,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun showCardresult(){
//    cardResult(modifier = Modifier)
}


/*******************************************************************/
/*****************        Drag and Drop           ******************/
/*******************************************************************/
/**
 * drag and drop step 1
 * fun DragContainer(modifier: Modifier,content:@Composable BoxScope.()->Unit) {
}
 */

/**
 * drag and drop step 2
 * Define  DragData and DragType
 *
 *
 * class DragData(val type: MimeType = MimeType.TEXT_PLAIN, val data:Any? = null)
 *
 * enum class DragType(val valuse:String){
 *     COMPONENT("compose component"),
 *     LAYOUT("compose layout"),
 *     LAZYLAYOUT("lazy layout")
 * }
 * */

/**
 * drag and drop step 3
 * fun DropTarget()
 *
 * @Composable
 * fun DragTarget(dragData:DragData,content:@Composable ()->Unit) {
 * }
 * */


/**
 * drag and drop step 4:
 * fun DropContainer()
 *
 * @Composable
 * fun DropContainer(modifier: Modifier,
 *                   onDrag:()->Unit,
 *                   content:@Composable BoxScope.(data:DragData?)->Unit){
 *
 * }
 * */
