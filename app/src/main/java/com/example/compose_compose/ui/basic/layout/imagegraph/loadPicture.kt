package com.example.compose_compose.ui.basic.layout.imagegraph

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
//        Image(
//            painter = painterResource(id = R.drawable.graphicssourceimagesmall),
//            contentDescription = stringResource(id = R.string.dog_content_description),
//            contentScale = ContentScale.Fit
//        )
        AsyncImage(
            model = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2Fb052a1aa-7a71-4225-be20-6f9c23c7f593%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1700111231&t=c87d4c244ddc93166376fcd5c1ac94b7",
            contentDescription = "Translated description of what the image contains"
        )

        GlideImage(
            model = "https://pic.rmb.bdstatic.com/bjh/fa6ac07b86809ffe0674fe0fe92893e9.jpeg",
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