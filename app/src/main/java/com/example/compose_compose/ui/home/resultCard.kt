package com.example.compose_compose.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
//        Canvas(modifier = modifier.weight(1f), onDraw = {})
        dropContent(modifier)
    }
}


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


    DropContainer(modifier = modifier, onDrag = {inBounds,isDragging->
        isDroppingItem = isDragging
        isItemInBounds = inBounds
    }) {dragData->
        val boxColor = if(isDroppingItem && isItemInBounds)
            MaterialTheme.colorScheme.onPrimaryContainer
        else MaterialTheme.colorScheme.onSecondaryContainer

        Canvas(modifier = modifier.fillMaxSize()) {
            dragData?.let {
                if (it.type == MimeType.TEXT_PLAIN) {
                    dragText = dragData.data as String
                }
                if (it.type == MimeType.IMAGE_JPEG) {
                    dragImage = dragData.data as Painter
                }
            }

        }

        DropPaneContent(null,null)
        CloseButton(updateDragText, updateDragImage)
    }
}

@Composable
fun DropPaneContent(dragText: String?, dragImage: Painter?) {
    if (dragText != null) {
        Text(
            text = dragText,
            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    } else if (dragImage != null) {
        Image(
            painter = dragImage,
            contentDescription = "",
//            modifier = Modifier.clip(Shapes.)
        )
    } else {
        Text(
            text = "",
            textAlign = TextAlign.Center,
//            style = MaterialTheme.typography.h5
        )
    }
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
    cardResult(modifier = Modifier)
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
