package com.example.compose_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.compose_compose.ui.home.homeContent
import com.example.compose_compose.ui.theme.ComposecomposeTheme
import com.example.compose_compose.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel:MainViewModel by viewModels()

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, true)

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
            val viewModel = hiltViewModel<MainViewModel>()
            homeContent(modifier, viewModel)
        }
        composable("base"){}
        composable("advanced"){}

        //nested navigation
        navigation(startDestination = "nestedgraph",route = "submodule"){
            composable(route="subhome"){}
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposecomposeTheme {
        Greeting("Compose")
    }
}