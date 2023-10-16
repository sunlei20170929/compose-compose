package com.example.compose_compose.imagegraph

import android.provider.Settings.Global.getString
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.compose_compose.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun firstPic(modifier:Modifier){
    Box(modifier=Modifier.padding(16.dp)){
        Image(
            painter = painterResource(id = R.drawable.graphicssourceimagesmall),
            contentDescription = stringResource(id = R.string.dog_content_description),
            contentScale = ContentScale.Fit
        )
        AsyncImage(
            model = "https://example.com/image.jpg",
            contentDescription = "Translated description of what the image contains"
        )

        GlideImage(
            model = "https://",
            contentDescription = null,
            modifier = Modifier.padding(12.dp).clickable(onClick = {}),
        )
    }
}

@Preview
@Composable
fun showLoadPic(){
    firstPic(modifier = Modifier)
}