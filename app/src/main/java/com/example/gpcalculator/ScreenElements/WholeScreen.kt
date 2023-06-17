package com.example.gpcalculator.ScreenElements

import GpCalculatorPrototype.Data.GpData
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    onEvent: (DialogBoxUiEvents) -> Unit,
    state : DialogBoxState,
    stateTwo: ArrayList<GpData>
){


    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(

        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()
    val sheetWidth = remember{
        mutableStateOf(60.dp)
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {ResultBottomSheetContent(state)},
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = Color.LightGray,
        modifier =  Modifier
            .fillMaxWidth()
            .width(sheetWidth.value)
           // .height(10.dp)
                ,
        sheetPeekHeight = 0.dp,
        drawerGesturesEnabled = true,
        sheetElevation = 100.dp
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    if(stateTwo.size < state.totalCourses.toInt()){

                        onEvent(DialogBoxUiEvents.showDataEntryDBox)


                    }
                    else{
                        onEvent(DialogBoxUiEvents.executeCalculation)
                        //onEvent(DialogBoxUiEvents.showResultDBox)
                        scope.launch {
                            if(sheetState.isCollapsed){
                                sheetState.expand()
                            }else{
                                sheetState.collapse()
                            }
                        }
                    }
                }) {

                    androidx.compose.material.Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Add,
                        contentDescription = "Add Course details",


                        )



                }
            }

        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (state.enteredCourses == "0"){
                        "Click the Add button!!!"
                    }else{
                        ""
                    }
                )
            }


            if(state.courseEntryDialogBoxVisibility){
                //CardViewToDisplay(data = CourseDataEntries().coursesDataEntry)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        CourseEntryDialogBox(onEvent = onEvent, dbState = state, title = "Enter your course details")

                    }

                }
            }
            else if(state.baseEntryDialogBoxVisibility){
                BaseEntryDialogBox(Desc = "Welcome please make your entries:", state = state, events = onEvent)

            }
            else if(state.resultDialogBoxVisibility){

                ResultDialogBox(desc = "Results", state = state, events = onEvent)
                //onEvent(DialogBoxUiEvents.resetResultField)

            }

            else{

                TotalCoursesListCardViewToDisplay(data = stateTwo)

            }





        }


    }



}