package com.example.compose_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.compose_compose.ui.basic.layout.animate.expandBox
import com.example.compose_compose.ui.basic.layout.animate.movedBox
import com.example.compose_compose.ui.basic.layout.component.myScaffold
import com.example.compose_compose.ui.basic.layout.gesture.basicScroll
import com.example.compose_compose.ui.basic.layout.gesture.dragSomething
import com.example.compose_compose.ui.basic.layout.gesture.interActionDemo
import com.example.compose_compose.ui.basic.layout.gesture.nestScroll
import com.example.compose_compose.ui.basic.layout.gesture.testScrollable
import com.example.compose_compose.ui.theme.ComposecomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            //屏幕尺寸类别
//            val windowSizeClass = calculateWindowSizeClass(this)

            //显示特性
//            val displayFeatures = calculateDisplayFeatures(this)


            ComposecomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //create navController
    val navController = rememberNavController()

    //create navHost
    NavHost(navController = navController, startDestination = "myhome"){
        composable("myhome"){
//            myScaffold(Modifier)
//            basicScroll(Modifier)
//            testScrollable(Modifier)
//            nestScroll(modifier = Modifier)
//            dragSomething(modifier = Modifier)
//            interActionDemo(Modifier)
//            expandBox(Modifier)
            movedBox(Modifier)
        }
        composable("base"){}
        composable("advanced"){}

        //nested navigation
        navigation(startDestination = "nestedgraph",route = "submodule"){
            composable(route="subhome"){}
        }
    }
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//
//    myScaffold(Modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposecomposeTheme {
        Greeting("Compose")
    }
}