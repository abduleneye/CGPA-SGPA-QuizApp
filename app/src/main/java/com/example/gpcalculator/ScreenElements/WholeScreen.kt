package com.example.gpcalculator.ScreenElements

import GpCalculatorPrototype.Data.GpData
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gpcalculator.ui.theme.Cream
import com.example.gpcalculator.ui.theme.lightBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    onEvent: (DialogBoxUiEvents) -> Unit,
    state : DialogBoxState,
    stateTwo: ArrayList<GpData>,
){



    val context = LocalContext.current

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(

        bottomSheetState = sheetState
    )

    var optionsMenuState by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()
    val sheetWidth = remember{
        mutableStateOf(60.dp)
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {ResultBottomSheetContent(state)},
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
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
            },
            topBar = {

                TopAppBar(

                    title = {

                        Text(text = "GpCalculator")
                    },
                    actions = {
                        IconButton(onClick = {

                            optionsMenuState = !optionsMenuState

                        }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "more")

                        }

                        DropdownMenu(expanded = optionsMenuState, onDismissRequest = {
                            optionsMenuState = false
                        },
                            modifier = Modifier
                                .background(Cream),
                                offset = DpOffset(0.0.dp, 2.0.dp)
                        ) {
                            
                            DropdownMenuItem(onClick = {


                                onEvent(DialogBoxUiEvents.resetTotalEntries)
                                optionsMenuState = !optionsMenuState
                                scope.launch {
                                    if(sheetState.isExpanded){
                                        sheetState.collapse()
                                    }
                                }

                            },
                                modifier = Modifier
                                    .background(Cream)
                                    .height(35.dp),


                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                    Text(text = "Reset", fontSize = 16.sp ,style = TextStyle(baselineShift = BaselineShift(0.199f)))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Icon(Icons.Default.Refresh, contentDescription = "settings",)
                                }
                            }



                        }

                    }

                )

            },

            backgroundColor = Cream



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
                    },
                    fontSize = 35.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    lineHeight = 1000.sp,


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



                BaseEntryDialogBox(Desc = "Welcome please make your entries", state = state, events = onEvent)

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