package com.example.compose_compose.imagegraph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalTextApi::class)
@Composable
fun firstImage(modifier: Modifier){
    Canvas(modifier = Modifier.fillMaxSize()){
        val canvasWidth = size.width
        val canvasHeight = size.height
        val canvasSize = size

        val canvasQuadrantSize = size / 2F

        //
//        val textMeasurer = rememberTextMeasurer()

        inset(50F, 30F) {
            drawRect(
                color = Color.Green,
                size = canvasQuadrantSize
            )

            withTransform({
                rotate(degrees = 45F)
                translate(left = canvasWidth/3f)
            })
            {
                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(x = canvasWidth / 3F, y = canvasHeight / 3F),
                    size = canvasSize / 3F
                )
            }
        }


        drawLine(
            start= Offset(x=canvasWidth,y=0f),
            end = Offset(x=0f,y=canvasHeight),
            color = Color.Blue,
            strokeWidth = 5f
        )

        drawCircle(
            color = Color.Blue,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension / 4
        )

//        drawText(textMeasurer,"Hello Canvas Draw Scope")

    }
}

@Preview
@Composable
fun showImage(){
    firstImage(modifier = Modifier)
}