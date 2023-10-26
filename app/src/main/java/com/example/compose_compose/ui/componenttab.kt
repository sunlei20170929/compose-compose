package com.example.compose_compose.ui

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow

fun getAllLayout(): MutableList<String> {

    val arr = ArrayList<String>()
    //pager
    arr.add("HorizontalPager")
    arr.add("VerticalPager")
    //flowlayout
    arr.add("FlowRow")
    arr.add("FlowColumn")

    arr.add("Column")
    arr.add("Row")
    arr.add("Box")
    arr.add("Scaffold")

    arr.add("Card")
    return arr

}
fun getAllComponents(): MutableList<String> {
    val componetNames = listOf(
        "Filled Button","tonal Button","Outlined Button","Text Button",
        "FAB","Small FAB","Large FAB","Extend FAB",
        "Assit Chip","Filter Chip","Input Chip","Suggestion Chip",
        "LinearProgressIndicator","CircularProgressIndicator",
        "Slider",
        "Switch",
        "ModalBottomSheet",
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