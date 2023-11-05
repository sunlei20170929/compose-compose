package com.example.compose_compose.ui

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow

fun getAllLayout(): MutableList<String> {

    val layouts = listOf(
        "HorizontalPager","VerticalPager",
        "FlowRow","FlowColumn",
        "Column","Row","Box","Scaffold","Card"
    )
    return layouts.toMutableList()

}
fun getAllComponents(): MutableList<String> {
    val componetNames = listOf(
//        "Filled Button","tonal Button","Outlined Button","Text Button",
        "Button",
        "FAB",
//        "Small FAB","Large FAB","Extend FAB",
//        "Assit Chip","Filter Chip","Input Chip","Suggestion Chip",
        "Chip",
        "LinearProgressIndicator","CircularProgressIndicator",
        "Slider",   "Switch",       "ModalBottomSheet",
        "ModalNavigationDrawer",
    )
    return componetNames.toMutableList()
}


fun getAllLazyLayout():MutableList<String>{
    val lazyLayouts = listOf(
        "LazyColumn","LazyRow",
        "LazyVerticalGrid","LazyHorizontalGrid",
        "LazyVerticalStaggeredGrid",
        "LazyHorizontalStaggeredGrid"
    )
    return lazyLayouts.toMutableList()
}