package com.example.compose_compose.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_compose.viewmodel.WidgetViewModel
import kotlinx.coroutines.launch

@Composable
fun WidgetScreen(onBackPressed:()->Unit,vm: WidgetViewModel
){

    val source by vm.files.collectAsState()
    val scope = rememberCoroutineScope()

    val deleteFile:(String)->Unit = {filename->
        vm.delete(filename)
    }

    val showFileCode:(String)->Unit = {
        scope.launch {
            vm.generateTreeCode(it)
        }

    }

    if(source.isEmpty()){
        Box(modifier = Modifier.fillMaxSize()){
            Text("There is no saved widget")
        }
    }else{
        LazyColumn{
            items(source){ composeFile ->
                itemContent(Modifier,composeFile,{deleteFile(composeFile)},{showFileCode(composeFile)},{
                    vm.generateTreeCode(it)})
            }
        }
    }


}

@Composable
fun itemContent(modifier:Modifier, fname:String, delete:((String)-> Unit)?,showCode:((String)->Unit)?,getCode:((String)->String)?){

    var showState by remember { mutableStateOf(false) }

    Column {
        Row(modifier = modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically){
            Text(modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
                .wrapContentWidth(Alignment.Start),

                style = MaterialTheme.typography.titleLarge,
                text = fname)
            Button(onClick = {
                if (delete != null) {
                    delete(fname)
                }
            }){
                Text(text="Delete")
            }
            Button(modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .wrapContentWidth(Alignment.End),
                onClick = {
                    showState = !showState
                }){
                Text(text="Generate Code")
            }
        }
        if (getCode != null) {
            AnimatedVisibility(visible  = showState,
                enter = slideInVertically ()
                        + expandVertically(expandFrom = Alignment.Top)
                        + fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically()
                        + shrinkVertically()
                        + fadeOut()){
                Text(modifier= Modifier
                    .padding(16.dp)
                    .background(color = MaterialTheme.colorScheme.background)
                    .wrapContentWidth(),text=getCode(fname))
            }

        }
    }

}

@Preview
@Composable
fun previewItem(){
    itemContent(modifier = Modifier, fname = "filename",null,null,null)
}

@Composable
fun showCodeScreen(filename:String){
    Box(modifier = Modifier.fillMaxSize()){

    }
}
