package com.example.compose_compose.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
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

@Composable
fun dropContent(modifier: Modifier){

    var dragText by remember { mutableStateOf<String?>(null) }
    var dragImage by remember{ mutableStateOf<Painter?>(null) }

    var isDroppingItem by remember { mutableStateOf(false) }
    var isItemInBounds by remember { mutableStateOf(false) }

    var reCompose by remember { mutableStateOf(false) }

    val viewModel = hiltViewModel<MainViewModel>()
//    val tree = rememberUpdatedState(newValue = viewModel.tree)
    val tree = remember {mutableStateOf(viewModel.tree)}


    val treeClear:()->Unit = {
        tree.value.clearTree(tree.value)
        reCompose = !reCompose
    }

    val saveTree:()->Unit = {

    }

    DropContainer(modifier = modifier.verticalScroll(rememberScrollState()), onDrag = { inBounds, isDragging ->
        isDroppingItem = isDragging
        isItemInBounds = inBounds
    }) { dragData ->
        val boxColor = if (isDroppingItem && isItemInBounds)
            MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.surface
        Column(
            modifier = modifier
                .fillMaxSize()
                .height(120.dp)
                .background(color = boxColor)
//                .verticalScroll(rememberScrollState())
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
                    Log.e("draw","tree addchild $dragText")
                    tree.value.addChild(dragText.toString())
                }
            }
        }

        DrawTree(modifier, nodes = tree.value,reCompose)
        CloseButton ({ treeClear() }, { saveTree() })

    }


}

@Composable
fun DrawTree(modifier:Modifier, nodes: TreeNode, redraw: Boolean){

    var dragText by remember { mutableStateOf<String?>(null) }
    var isDroppingItem by remember { mutableStateOf(false) }
    var isItemInBounds by remember { mutableStateOf(false) }

    val boxColor = if(isDroppingItem && isItemInBounds && redraw)
        MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.surface

    Column {
        for (node in nodes.children) {
            DropContainer(modifier = modifier, onDrag = {inBounds,isDragging->
                isDroppingItem = isDragging
                isItemInBounds = inBounds
            }) {dragData->
                Column(modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    .height(60.dp)
                    .fillMaxWidth()
                ) {
                    Text(text = node.name)
                    dragData?.let {
                        if (it.type == MimeType.TEXT_PLAIN) {
                            dragText = dragData.data as String

                            val subtree = rememberUpdatedState(newValue = nodes)
                            if(isItemInBounds && !isDroppingItem){
                                LaunchedEffect(key1 = Unit) {
                                    Log.e("draw","${node.name} addsubchild $dragText")
                                    node.addSubChild(node,dragText.toString())
                                }
                            }
                        }
                    }
                }
            }
            Row(modifier.padding(horizontal = 10.dp)
            ){
                DrawTree(modifier =Modifier.background(color = boxColor), nodes = node, redraw)
            }
        }

    }
}

@Composable
fun CloseButton(
    clearTree: () -> Unit,
    saveTree:()->Unit,
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val openSaveDialog = remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            IconButton(onClick = { openSaveDialog.value = true }) {
                Icon(tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    imageVector = Icons.Filled.Done,
                    contentDescription="save")
            }
            IconButton(
                onClick = {
                    openAlertDialog.value = true
                }
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    imageVector = Icons.Filled.Close,
                    contentDescription = ""
                )
            }
            when {
                openAlertDialog.value -> {
                    delTreeAlertDialog(
                        onDismissRequest = { openAlertDialog.value = false },
                        onConfirmation = {
                            openAlertDialog.value = false
                            clearTree()
                        },
                        dialogTitle = "Delete Tree",
                        dialogText = "Delete all the nodes of the tree.",
                        icon = Icons.Default.Info
                    )
                }
            }

            when
            {
                openSaveDialog.value ->{
                    saveTreeDialog(
                    onDismissRequest = { openSaveDialog.value = false },
                    onConfirmation = {
                        openSaveDialog.value = false
                        saveTree()
                    },
                        onInputChanged = {}

                    )
                }
            }
        }

    }
}

fun saveTree(){

}
@Preview
@Composable
fun previewResult(){
    val subsubnodes = listOf(TreeNode("sub-sub-child1","sub-child2"),TreeNode("sub-sub-child2","sub-child2"))
    val subnodes = listOf(TreeNode("sub-child1","child1"),TreeNode("sub-child2","child1"))
    val root:TreeNode = TreeNode("root",null).apply {
        children = listOf(TreeNode("child1","root"),TreeNode("child2","root"))
        children[1].children = subnodes
//        children[1].children = subnodes
        children[1].children[0].children = subsubnodes
        children[1].children[1].children = subsubnodes
    }

    val tmp = remember { mutableStateOf(false) }

    DrawTree(Modifier, nodes = root,false)
}

// to reset drag and drop


@Preview
@Composable
fun showCardresult(){
//    cardResult(modifier = Modifier)
}
