package com.example.compose_compose.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardContent(modifier: Modifier){
    //a Card wraps its content in a Column composable
//    Card(
//    ElevatedCard(
    OutlinedCard(
        border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
//        elevation = CardDefaults.cardElevation(defaultElevation = 60.dp),
        modifier = Modifier.size(width=240.dp,height = 120.dp)
    ){
        Text(text="card content",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.End)
    }
}

@Preview
@Composable
fun previewCard(){
    CardContent(Modifier)
}