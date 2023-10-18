package com.example.compose_compose.ui.basic.layout.gesture

import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.collect

@Composable
fun interActionDemo(modifier: Modifier){

    val interactionSource  = remember {MutableInteractionSource()}
    val isPressed by interactionSource.collectIsPressedAsState()

    val interactions = remember { mutableStateListOf<Interaction>() }

    LaunchedEffect(key1 = interactionSource){
        interactionSource.interactions.collect{interaction->
            when(interaction){
                is PressInteraction.Press ->{
                    interactions.add(interaction)
                }
                is PressInteraction.Release -> {
                    interactions.remove(interaction.press)
                }
                is PressInteraction.Cancel -> {
                    interactions.remove(interaction.press)
                }
                is DragInteraction.Start -> {
                    interactions.add(interaction)
                }
                is DragInteraction.Stop -> {
                    interactions.remove(interaction.start)
                }
                is DragInteraction.Cancel -> {
                    interactions.remove(interaction.start)
                }
            }
        }
    }

    Button(
        onClick = {},
        interactionSource = interactionSource){
        Text(if(isPressed) "Pressed" else "Nothing")
    }

}

@Preview
@Composable
fun showInterAction(){
    interActionDemo(modifier = Modifier)
}