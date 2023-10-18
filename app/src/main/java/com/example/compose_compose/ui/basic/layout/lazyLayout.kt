package com.example.compose_compose.ui.basic.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun lazyLayout(modifier: Modifier) {

    /**
     * LazyColumn and LazyRow
     * LazyVerticalGrid and LazyHorizontalGrid  ( columns = GridCells.Adaptive(minSize = 128.dp))
     * LazyVerticalStaggeredGrid and LazyHorizontalStaggeredGrid(columns = StaggeredGridCells.Fixed(3),)
     *====================
     *==   parameters： ==
     * ===================
     *   contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
     *   verticalArrangement = Arrangement.spacedBy(4.dp),
     *
     * ====================
     * ==   scope：      ==
     * ====================
     * >>>>>>>>    key    <<<<<<<<
     * items(
     *         items = messages,
     *         key = { message ->
     *             // Return a stable + unique key for the item
     *             message.id
     *         }
     *     )
     *
     *>>>>>>>>    animation    <<<<<<<<
     *  items(books, key = { it.id }) {
     *         Row(Modifier.animateItemPlacement()) {
     *             // ...
     *         }
     *     }
     *
     *  items(books, key = { it.id }) {
     *  //custom animation
     *         Row(Modifier.animateItemPlacement()) {
     *             // ...
     *         }
     *     }
     * >>>>>>>>    sticky header    <<<<<<<<
     *  stickyHeader {
     *             Header()
     *         }
     * >>>>>>>>    group    <<<<<<<<
     * grouped.forEach { (initial, contactsForInitial) ->
     *             stickyHeader {
     *                 CharacterHeader(initial)
     *             }
     *
     *             items(contactsForInitial) { contact ->
     *                 ContactListItem(contact)
     *             }
     *         }
     *
     *  ===================
     *  ==   state：     ==
     *  ===================
     *   val listState = rememberLazyListState()
     *
     *     // Provide it to LazyColumn
     *     LazyColumn(state = listState) {...}
     *
     *    LazyListState provides
     *        the firstVisibleItemIndex and firstVisibleItemScrollOffset and  layoutInfo properties
     *        scrollToItem() and animateScrollToItem() methods
     *
     *    snapshotFlow { listState.firstVisibleItemIndex }
     *
     *
     *    ===================
     *    ==   page3       ==
     *    ===================
     *    fun MessageList(pager: Pager<Int, Message>) {
     *     val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
     *
     *     LazyColumn {
     *         items(
     *             lazyPagingItems.itemCount,
     *             key = lazyPagingItems.itemKey { it.id }
     *         ) { index ->
     *             val message = lazyPagingItems[index]
     *             if (message != null) {
     *                 MessageRow(message)
     *             } else {
     *                 MessagePlaceholder()
     *             }
     *         }
     *     }
     * }
     *
     * Tips：
     * 1、Avoid using 0-pixel sized items（set default sizing to your items）
     * 2、Avoid nesting components scrollable in the same direction
     * 3、Beware of putting multiple elements in one item
     * */
}