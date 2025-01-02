package com.eneye.enquizgpa.presentation.myViewModels.course_list_screen_component

import GpCalculatorPrototype.Data.FourGpData
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.data.FiveCourseItemsModifierDropDownItems
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.eneye.enquizgpa.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.eneye.enquizgpa.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.eneye.enquizgpa.ui.theme.Cream
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FourSgpaTotalCoursesListCardViewToDisplay(
    data: ArrayList<FourGpData>,
    onClickEvent: (FourGpaUiEvents) -> Unit,
    dbState: FourSgpaUiStates,
    sheetState: BottomSheetState,
    helperPaddingValues: PaddingValues,
) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()


    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            // .height(550.dp)
            .fillMaxHeight(1f)
            .padding(
                top = helperPaddingValues.calculateTopPadding(),
//                start = 16.dp,
//                end = 16.dp,

                bottom = 164.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        // contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(items = data, key = { id, listItem ->
            listItem.hashCode()
        }) { index, item ->

            val context = LocalContext.current

            FourSgpaMyCardViewForCoursesList(
                info = item,
                index = index,
                onEvent = onClickEvent,
                state = FiveSgpaUiStates(),
                dropDownItems = listOf(

                    FiveCourseItemsModifierDropDownItems("Edit"),
                    FiveCourseItemsModifierDropDownItems("Remove")

                ),
                onMenuItemClick = {

                    if (it.text == "Remove") {
                        try {
                            onClickEvent(FourGpaUiEvents.deleteCourseEntry(index))


                        } catch (e: Exception) {
//                            Toast.makeText(
//                                context, "Course already entered", Toast.LENGTH_SHORT
//                            ).show()
                        }
                    } else if (it.text == "Edit") {

                        onClickEvent(FourGpaUiEvents.updateCourseIndexEntry(index.toString()))
                        onClickEvent(
                            FourGpaUiEvents.editItemsEntries(
                                item.courseCode,
                                item.courseGrade,
                                item.courseUnit.toString()
                            )
                        )
                        onClickEvent(FourGpaUiEvents.showCourseEntryEditDBox)

                    }
                },
                onItemClick = onClickEvent,
                sheetState = sheetState


            )

        }


    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FourSgpaMyCardViewForCoursesList(
    info: FourGpData,
    index: Int,
    onEvent: (FourGpaUiEvents) -> Unit,

    dropDownItems: List<FiveCourseItemsModifierDropDownItems>,
    modifier: Modifier = Modifier,
    state: FiveSgpaUiStates,
    onItemClick: (FourGpaUiEvents) -> Unit,
    onMenuItemClick: (FiveCourseItemsModifierDropDownItems) -> Unit,
    sheetState: BottomSheetState,


    ) {


    val context = LocalContext.current
    var pressOffset by remember {
        mutableStateOf(DpOffset((0).dp, 80.dp))
    }

    val density = LocalDensity.current
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var itemHeight by remember {
        mutableStateOf(80.dp)
    }

    var isContextMenuVisible by remember {
        mutableStateOf(false)
    }
    val myContext = LocalContext.current

    val scope = rememberCoroutineScope()




    Card(
        ///
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
//            .height(90.dp)
//            .fillMaxWidth()
//            .padding(
//                // horizontal = 16.dp, vertical = 8.dp
//                top = 16.dp,
//                bottom = 4.dp,
//                start = 16.dp,
//                end = 16.dp
//            ),
            .height(140.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Cream
        )
        ///
        //colors = CardDefaults.cardColors(containerColor = Cream),
//        shape = RoundedCornerShape(16.dp),
//        //elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        elevation = 8.dp,
//
//        modifier = Modifier
//            .onSizeChanged {
//                itemHeight = with(density) { it.height.toDp() }
//            }
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//            // .fillMaxWidth()
//            .height(70.dp)
//            .background(color = Cream)

    ) {
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
//                .indication(interactionSource = interactionSource, LocalIndication.current)
//                .pointerInput(true) {
//                    detectTapGestures(
//                        onLongPress = {
//                            //onItemClick(FourGpaUiEvents.showCourseDataEntriesContextmenu)
////                            isContextMenuVisible = true
////                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
////                            onItemClick(FourGpaUiEvents.showCourseDataEntriesContextmenu)
////                            scope.launch {
////                                if (sheetState.isExpanded) {
////                                    sheetState.collapse()
////                                }
////                            }
//
//
//                        },
//                        onPress = {
//                            isContextMenuVisible = true
//                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
//                            onItemClick(FourGpaUiEvents.showCourseDataEntriesContextmenu)
//                            scope.launch {
//                                if (sheetState.isExpanded) {
//                                    sheetState.collapse()
//                                }
//                            }
//
//
////                            val press = PressInteraction.Press(it)
////                            interactionSource.emit(press)
////                            tryAwaitRelease()
////                            interactionSource.emit(PressInteraction.Release(press))
//                        }
//                    )
//
//                }
                .padding(top = 8.dp)


        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = info.courseCode,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(0.9f)
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                            .padding(start = 45.dp),
                        // textAlign = Alignment.CenterVertically
                    )

                    IconButton(
                        modifier = Modifier
                            .weight(0.1f)
                            .padding(bottom = 16.dp),
                        onClick = {
                            onItemClick(FourGpaUiEvents.showCourseDataEntriesContextmenu)
                            isContextMenuVisible = true
                            onItemClick(FourGpaUiEvents.showCourseDataEntriesContextmenu)
                            scope.launch {
                                if (sheetState.isExpanded) {
                                    sheetState.collapse()
                                }
                            }

                        },


                        ) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Edit and delete options",
                            modifier = Modifier.size(24.dp)
                        )

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = info.courseGrade, fontWeight = FontWeight.Bold)
                    Text(text = info.courseUnit.toString(), fontWeight = FontWeight.Bold)

                }

            }

        }

        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = {
                isContextMenuVisible = false
                onEvent(FourGpaUiEvents.hideCourseDataEntriesContextmenu)
            },
            offset =
            pressOffset.copy(
                x = (-16).dp * LocalDensity.current.density,
                y = 2.dp

            ),
            modifier = Modifier
                .background(Cream)
                .weight(1f)
                .widthIn(max = 200.dp)
            // .padding(end = 64.dp)
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onMenuItemClick(item)
                        isContextMenuVisible = false
                        onEvent(FourGpaUiEvents.hideCourseDataEntriesContextmenu)
                    }) {

                    Text(text = item.text)

                }

            }
        }
    }
}

