package com.example.compose_compose.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.compose_compose.ui.home.layouttab.composableContainer
import com.example.compose_compose.viewmodel.MainViewModel
import com.microsoft.device.dualscreen.draganddrop.DragContainer
import kotlinx.coroutines.launch


enum class ComponentType{
    LAYOUT,LAZYLAYOUT,COMPONENT
}
val pages = ComponentType.values()
val tabTitles = listOf("layout","lazylayout","component")
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun homeContent(modifier: Modifier,vm:MainViewModel?){

    val pagerState = rememberPagerState(0,0f) { 3 }

    DragContainer(modifier = Modifier.fillMaxSize()){
        Scaffold() {
            Column(modifier.nestedScroll(rememberNestedScrollInteropConnection())){
                homeTab(pagerState)
                homePager(modifier = modifier,pagerState)
                //result
                cardResult(modifier)
            }
        }
    }

}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun homeTab(pagerState: PagerState){

    val coroutineScope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage){
        pages.forEachIndexed {index,page->
            Tab(
                selected = pagerState.currentPage.equals(ComponentType.LAYOUT),
                onClick = {coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }},
                text = { Text(text = tabTitles[index]) },
                unselectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                selectedContentColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun homePager(modifier: Modifier,pageState:PagerState){
    HorizontalPager(
        modifier = Modifier,
        state = pageState,
    ){index->
        composableContainer(modifier,index)
    }
}
@Preview
@Composable
fun previewHomeContent(){
    homeContent(modifier = Modifier,null)
}