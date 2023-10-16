package com.example.compose_compose.imagegraph

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun firstPic(modifier:Modifier){
    Box(modifier=Modifier.padding(16.dp)){
//        Image(
//            painter = painterResource(id = R.drawable.dog),
//            contentDescription = stringResource(id = R.string.dog_content_description)
//        )
    }
}

@Preview
@Composable
fun showLoadPic(){
    firstPic(modifier = Modifier)
}