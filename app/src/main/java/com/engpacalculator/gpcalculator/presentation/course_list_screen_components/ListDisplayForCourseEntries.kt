package com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component

import GpCalculatorPrototype.Data.GpData
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.engpacalculator.gpcalculator.data.CourseItemsModifierDropDownItems
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.DialogBoxState
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.DialogBoxUiEvents
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TotalCoursesListCardViewToDisplay(
    data: ArrayList<GpData>,
    onClickEvent: (DialogBoxUiEvents) -> Unit,
    dbState: DialogBoxState,
    sheetState: BottomSheetState
) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()


    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        // contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(items = data, key = { id, listItem ->
            listItem.hashCode()
        }) { index, item ->

            val context = LocalContext.current

            MyCardView(
                info = item,
                index = index,
                onEvent = onClickEvent,
                state = DialogBoxState(),
                dropDownItems = listOf(

                    CourseItemsModifierDropDownItems("Edit"),
                    CourseItemsModifierDropDownItems("Delete")

                ),
                onMenuItemClick = {

                    if (it.text == "Delete") {
                        try {
                            onClickEvent(DialogBoxUiEvents.deleteCourseEntry(index))


                        } catch (e: Exception) {
//                            Toast.makeText(
//                                context, "Course already entered", Toast.LENGTH_SHORT
//                            ).show()
                        }
                    } else if (it.text == "Edit") {

                        onClickEvent(DialogBoxUiEvents.updateCourseIndexEntry(index.toString()))
                        onClickEvent(
                            DialogBoxUiEvents.editItemsEntries(
                                item.courseCode,
                                item.courseGrade,
                                item.courseUnit.toString()
                            )
                        )
                        onClickEvent(DialogBoxUiEvents.showCourseEntryEditDBox)

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
fun MyCardView(
    info: GpData,
    index: Int,
    onEvent: (DialogBoxUiEvents) -> Unit,

    dropDownItems: List<CourseItemsModifierDropDownItems>,
    modifier: Modifier = Modifier,
    state: DialogBoxState,
    onItemClick: (DialogBoxUiEvents) -> Unit,
    onMenuItemClick: (CourseItemsModifierDropDownItems) -> Unit,
    sheetState: BottomSheetState


) {


    val context = LocalContext.current
    var pressOffset by remember {
        mutableStateOf(DpOffset((-10).dp, 80.dp))
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
            .height(90.dp)
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
                .height(90.dp)
                .indication(interactionSource = interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            //onItemClick(DialogBoxUiEvents.showCourseDataEntriesContextmenu)
                            isContextMenuVisible = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                            onItemClick(DialogBoxUiEvents.showCourseDataEntriesContextmenu)
                            scope.launch {
                                if (sheetState.isExpanded) {
                                    sheetState.collapse()
                                }
                            }


                        },
                        onPress = {
//                            val toast = Toast
//                                .makeText(
//                                    myContext,
//                                    "Long press to edit or delete",
//                                    Toast.LENGTH_SHORT
//                                )
//                                .show()


                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        }
                    )

                }
                .padding(top = 8.dp)


        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = info.courseCode, fontWeight = FontWeight.Bold)
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
                onEvent(DialogBoxUiEvents.hideCourseDataEntriesContextmenu)
            },
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight

            ),
            modifier = Modifier.background(Cream)
        ) {
            dropDownItems.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onMenuItemClick(item)
                        isContextMenuVisible = false
                        onEvent(DialogBoxUiEvents.hideCourseDataEntriesContextmenu)
                    }) {

                    Text(text = item.text)

                }

            }
        }
    }
}

