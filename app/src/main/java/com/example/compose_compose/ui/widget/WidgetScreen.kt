package com.example.compose_compose.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_compose.viewmodel.WidgetViewModel

@Composable
fun WidgetScreen(onBackPressed:()->Unit,
                 vm: WidgetViewModel
){

    val source by vm.files.collectAsState()

    val deleteFile:(String)->Unit = {filename->
        vm.delete(filename)
    }

    val showFileCode:(String)->Unit = {

    }

    LazyColumn{
        items(source,key={ source.iterator()}){ composeFile ->
            itemContent(Modifier,composeFile,{deleteFile(composeFile)},{showFileCode(composeFile)})
        }
    }
}

@Composable
fun itemContent(modifier:Modifier, fname:String, delete:((String)-> Unit)?,showCode:((String)->Unit)?){
    Row(modifier = modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically){
        Text(modifier = Modifier
            .weight(1f)
            .padding(start=16.dp)
            .wrapContentWidth(Alignment.Start),
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
                if (showCode != null) {
                    showCode(fname)
                }
            }){
            Text(text="Generate Code")
        }
    }
}

@Preview
@Composable
fun previewItem(){
    itemContent(modifier = Modifier, fname = "filename",null,null)
}

