package com.example.compose_compose.ui.basic.layout.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun firstDialog(
    onDismissRequest:()->Unit,
    onConfirmation:()->Unit,
    dialogTitle:String,
    dialogText:String,
    icon: ImageVector,
){
    AlertDialog(
        icon = { Icon(icon,contentDescription = "dialog icon")  },
        title = { Text(text = dialogTitle)},
        text = { Text(text = dialogText)},
        onDismissRequest = { onDismissRequest() },
        confirmButton = {  TextButton(
            onClick = {
                onConfirmation()
            }
        ) {
            Text("Confirm")
        } },
        dismissButton = {
            TextButton(onClick = { onDismissRequest }) {
                Text("Dismiss")
            }
        }
        )

}


@Composable
fun showDialog(){
    var openAlertDialog by remember { mutableStateOf(true) }

    when{
        openAlertDialog->{
            firstDialog(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog =  false
                    println("do handle the confirm")
                },
                dialogTitle = "Dialog Title",
                dialogText = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info
            )

        }
    }
}