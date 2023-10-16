package com.example.compose_compose.component

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.OvalShape

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myScaffold(modifier: Modifier)  {

    var presses = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {slotOfTopAppBar(modifier = Modifier)},
        bottomBar = {slotOfBottomBar(Modifier)},
        floatingActionButton = {slotOfFAB(Modifier) }
    ) {
        Column(modifier = Modifier.padding(it), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an demo of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )

            var curProgress by remember { mutableStateOf(0f) }
            var loading by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()


            Button(onClick={
                loading = true
                scope.launch {
                    //run update lambda in a coroutine scope
                    loadingProgress{progress->
                        //update ui
                        curProgress = progress
                    }
                    loading = false
                }
            },enabled = !loading){
                Text("start loading")
            }

            if(loading)
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth(),progress=curProgress)

            CardContent(modifier = Modifier)
        }
    }
}

suspend fun loadingProgress(updateProgress:(Float)->Unit){
    for(i in 1..100){
        updateProgress(i.toFloat()/100)  //modify value and update ui
        delay(200)
    }


}

@Preview
@Composable
fun showScaffold(){
    myScaffold(modifier = Modifier)
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun slotOfTopAppBar(modifier: Modifier){

    val topAppBarColors = listOf<Color>(MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.primary)

    //for Center aligned AppBar
    val scrollBehavier = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    //for MediumTopAppBar
    val scrollBehaviorforMedium = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    //for LargeTopAppBar
    val scrollBehaviorforLarge = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
//                 CenterAlignedTopAppBar(
//                 MediumTopAppBar(
        LargeTopAppBar(
            //for app bar scroll with Medium and Large
            modifier = Modifier.nestedScroll(scrollBehavier.nestedScrollConnection),
            title = { Text("Title of TopAppBar") },
//                     colors = topAppBarColors(
//                         containerColor = MaterialTheme.colorScheme.primaryContainer,
//                         titleContentColor = MaterialTheme.colorScheme.primary,
//                     ),
            navigationIcon = {
                IconButton(onClick={}){
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "localization description")
                }
            },
            actions = {
                IconButton(onClick = {}){
                    Icon(imageVector = Icons.Filled.Menu,
                        contentDescription = "menu")
                }

            },
            scrollBehavior = scrollBehaviorforLarge  /**/
        )

}

@Composable
fun slotOfBottomBar(modifier: Modifier){
    BottomAppBar(actions = {
        //bottom app bar with icon
        IconButton(onClick = {}){
            Icon(Icons.Filled.Check,contentDescription = null)
        }
        IconButton(onClick = {}){
            Icon(Icons.Filled.Edit,contentDescription = null)
        }
        IconButton(onClick = { }) {
            Icon(Icons.Filled.FavoriteBorder,contentDescription = null)
        }
    })/*{

//                        Text( modifier = Modifier.fillMaxWidth(),
//                            textAlign = TextAlign.Center,
//                            textDecoration = TextDecoration.Underline,
//                            text = "Bottom app bar",)
                    }*/
}

@Composable
fun slotOfFAB(modifier: Modifier){
    LargeFloatingActionButton(onClick = { },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary,
        shape = CircleShape,
//        icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
//        text = { Text(text="extend fab") }
        ) {
        Icon(Icons.Filled.Add, "Small floating action button.")
    }
}
