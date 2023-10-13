package com.example.compose_compose.component

import android.annotation.SuppressLint

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myScaffold(modifier: Modifier)  {

    var presses = remember { mutableStateOf(0) }
    val topAppBarColors = listOf<Color>()

    Scaffold(
        topBar = {
                 TopAppBar(title = { Text("Title of TopAppBar") },
//                     colors = topAppBarColors(
//                         containerColor = MaterialTheme.colorScheme.primaryContainer,
//                         titleContentColor = MaterialTheme.colorScheme.primary,
//                     )
                                      )
        },
        bottomBar = {
                    BottomAppBar {
                        Text( modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline,
                            text = "Bottom app bar",)
                    }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses.value++ }) {
               Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
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
        }
    }
}

@Preview
@Composable
fun showScaffold(){
    myScaffold(modifier = Modifier)
}
