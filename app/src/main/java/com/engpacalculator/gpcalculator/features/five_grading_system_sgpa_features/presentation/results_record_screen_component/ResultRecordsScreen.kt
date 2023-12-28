package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.results_record_screen_component

import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.UniFiveSgpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.course_list_screen_components.DialogBoxState
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.launch


@Composable
fun Init() {
    Box {
        Text(text = "Holla")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecordScreen(
    navController: NavController,
    state: List<UniFiveSgpaResultEntity>,
    viewModel: UniFiveSgpaViewModel,

    ) {

    val scope = rememberCoroutineScope()


    LaunchedEffect(key1 = true) {

        scope.launch {
            viewModel.loadData()

        }


    }
    //

    Init()

    ResultRecordToDisplay(
        data = state,
    )

    //
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCardView(
    info: UniFiveSgpaResultEntity,
    index: Int,
    modifier: Modifier = Modifier,
    state: DialogBoxState,


    ) {


    val myContext = LocalContext.current

    val scope = rememberCoroutineScope()




    Card(
        ///
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(320.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Cream
        )


    ) {
        Box(

            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
//                    .indication(interactionSource = interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            //onItemClick(DialogBoxUiEvents.showCourseDataEntriesContextmenu)
//                            isContextMenuVisible = true
//                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
//                            onItemClick(DialogBoxUiEvents.showCourseDataEntriesContextmenu)
//                            scope.launch {
//                                if (sheetState.isExpanded) {
//                                    sheetState.collapse()
//                                }
//                            }


                        },
//                            onPress = {
//                                isContextMenuVisible = true
//                                pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
//                                onItemClick(DialogBoxUiEvents.showCourseDataEntriesContextmenu)
//                                scope.launch {
//                                    if (sheetState.isExpanded) {
//                                        sheetState.collapse()
//                                    }
//                                }
//
//
////                            val press = PressInteraction.Press(it)
////                            interactionSource.emit(press)
////                            tryAwaitRelease()
////                            interactionSource.emit(PressInteraction.Release(press))
//                            }
                    )

                }
                .padding(top = 8.dp)


        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = info.resultEntries.toString(), fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = info.gp, fontWeight = FontWeight.Bold)
                    Text(text = info.remark, fontWeight = FontWeight.Bold)

                }

            }

        }


    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultRecordToDisplay(
    data: List<UniFiveSgpaResultEntity>,
) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()


    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(1024.dp),
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
                state = DialogBoxState(),


                )

        }


    }


}
