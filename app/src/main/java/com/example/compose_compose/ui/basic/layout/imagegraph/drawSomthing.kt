package com.example.compose_compose.ui.basic.layout.imagegraph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope

import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.compose_compose.R

@OptIn(ExperimentalTextApi::class)
@Composable
fun myDraw(modifier:Modifier){
    /**
     * Modifier.drawWithContent
     * Modifier.drawBehind
     * Modifier.drawWithCache
     * */

    val textMeasurer = rememberTextMeasurer()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(32.dp)
    ) {
//        Spacer(
//            modifier = Modifier
//                .drawWithCache {
//                    val measuredText = textMeasurer.measure(
//                        AnnotatedString("longTextSample"),
//                        constraints = Constraints.fixedWidth((size.width * 2f / 3f).toInt()),
//                        style = TextStyle(fontSize = 18.sp)
//                    )
//
//                    onDrawBehind {
//                        drawRect(Color.Cyan, size = measuredText.size.toSize())
//                        drawText(measuredText)
//                    }
//                }
//
//        )

        //Modifier.drawWithContent确定可组合项的绘制顺序以及从修饰符内发出的绘制命令
        //Modifier.drawBehind可组合项内容后面执行 DrawScope 操作
        Column(modifier = Modifier
            .drawBehind {
            drawRoundRect(Color(0XFFBBAAEE), cornerRadius = CornerRadius(10.dp.toPx()))
            }
            .padding(4.dp)
        ){
            Text("Hello drawBehind")
            Text("Hello drawBehind",modifier = Modifier.flipped())
        }


        //Modifier.drawWithCache：绘制和缓存绘制对象
        Text("Hello drawWithCache",
            modifier = Modifier.drawWithCache {
                val brush = Brush.linearGradient(listOf(Color(0xFF9E82F0),Color(0xFF42A5F5)))
                onDrawBehind{
                    drawRoundRect(brush, cornerRadius = CornerRadius(10.dp.toPx()))
                }
            })

        //Modifier.graphicsLayer 是一个修饰符，用于将可组合项的内容绘制到绘图层
        //对可组合项应用转换:缩放,平移,旋转
        Image(painter = painterResource(id = R.drawable.graphicssourceimagesmall),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(120.dp).aspectRatio(1f).background(Brush.linearGradient(
                listOf(
                    Color(0xFFC5E1A5),
                    Color(0xFF80DEEA)
                )
            ))
                .padding(8.dp)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithCache {
                    val path = Path()
                    path.addOval(Rect(topLeft = Offset.Zero, bottomRight = Offset(size.width,size.height)))
                    onDrawWithContent {
                        clipPath(path){
                            this@onDrawWithContent.drawContent()
                        }

                        val dotSize = size.width/8f
                        val center = Offset(x=size.width-dotSize,y=size.height-dotSize)
                        drawCircle(Color.Black, radius = dotSize,center=center, blendMode = BlendMode.Clear)
                        drawCircle(Color(0xFFEF5350), radius = dotSize*0.8f,center=center)
                    }
                }
        )
        Spacer(modifier = Modifier.size(36.dp))

        Canvas(
            modifier = Modifier.size(200.dp)
        ) {
            drawSquares()
        }
        Spacer(modifier = Modifier.size(36.dp))
        Canvas(modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                alpha = 0.5f
            }) {
            drawSquares()
        }
        Spacer(modifier = Modifier.size(36.dp))
        Canvas(modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.ModulateAlpha
                alpha = 0.75f
            }) {
            drawSquares()
        }
    }

}
val Purple = Color(0xFF7E57C2)
val Yellow = Color(0xFFFFCA28)
val Red = Color(0xFFEF5350)
private fun DrawScope.drawSquares(){
    val size = Size(100.dp.toPx(), 100.dp.toPx())
    drawRect(color = Red, size = size)

    drawRect(
        color = Purple, size = size,
        topLeft = Offset(size.width / 4f, size.height / 4f)
    )
    drawRect(
        color = Yellow, size = size,
        topLeft = Offset(size.width / 4f * 2f, size.height / 4f * 2f)
    )
}
class FlippedModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        scale(1f, -1f) {
            this@draw.drawContent()
        }
    }
}

fun Modifier.flipped() = this.then(FlippedModifier())
@Preview
@Composable
fun showMyDraw(){
    myDraw(modifier = Modifier)
}