package com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component

import GpCalculatorPrototype.Data.GpData
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.presentation.ads_components.ShimmerBottomHomeBarItemAd
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.BaseEntryDialogBox
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.ConfirmClearCoursesEntryConfirmationDialogBox
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.CourseEntryDialogBox
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.DialogBoxState
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.DialogBoxUiEvents
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.EditBaseEntryDialogBox
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.ResultBottomSheetContent
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.TopAppBarDropDownMenu
import com.engpacalculator.gpcalculator.presentation.course_list_screen_components.gpcalculator_view_model
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    onEvent: (DialogBoxUiEvents) -> Unit,
    state: DialogBoxState,
    stateTwo: ArrayList<GpData>,
    calcViewModel: gpcalculator_view_model,
    navController: NavController,
    adId: String
) {


//    (Toast.makeText(
//        navController.context,
//        "cur loc:  $current: ${navBackStackEntry?.destination?.route} but",
//        Toast.LENGTH_SHORT
//    ).show())
    //onBackPressedDispatcher.onBackPressed()


    val context = LocalContext.current

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(

        bottomSheetState = sheetState
    )


    val scope = rememberCoroutineScope()
    val sheetWidth = remember {
        mutableStateOf(60.dp)
    }


    val iv = remember {
        mutableStateOf(Icons.Default.Add)
    }
    var initial_working_StatusIcon = if (state.totalCourses == state.enteredCourses) {
        Icons.Default.Done

    } else {
        Icons.Default.Add


    }

    var finalStatusIcon = Icons.Filled.Done


    /////////////


    BottomSheetScaffold(
//        onDismissRequest = {
//            scope.launch {
//                if (sheetState.isExpanded) {
//                    sheetState.collapse()
//                }
//            }
//
//        },

        scaffoldState = scaffoldState,
        sheetContent = { ResultBottomSheetContent(state, sheetState) },
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .width(sheetWidth.value)
            .background(color = Cream),

        // .height(10.dp)
        sheetPeekHeight = 0.dp,
        drawerGesturesEnabled = true,
        sheetElevation = 100.dp,
        backgroundColor = Cream,

        ) {


        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {


                        if (state.totalCourses == ""
                        //|| state.totalCreditLoad == ""
                        ) {

                            onEvent(DialogBoxUiEvents.showBaseEntryDBox)

                        } else if (stateTwo.size < state.totalCourses.toInt()) {

                            onEvent(DialogBoxUiEvents.showDataEntryDBox)
//                            Toast.makeText(
//                                context,
//                                "list  size: ${stateTwo.size} TONOEC: ${state.totalCourses}",
//                                Toast.LENGTH_LONG
//                            ).show()


//                    } else if (state.totalCreditLoad == "" || state.totalCourses == "") {
//                        onEvent(DialogBoxUiEvents.showBaseEntryDBox)

                        } else {
                            onEvent(DialogBoxUiEvents.executeCalculation)

                            //onEvent(DialogBoxUiEvents.showResultDBox)
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        }
                    },
                    backgroundColor = AppBars,

                    ) {

                    androidx.compose.material.Icon(
                        imageVector = initial_working_StatusIcon,
                        contentDescription = "Add Course details",


                        )


                }
            },
            topBar = {

                TopAppBarDropDownMenu(
                    onEvent = onEvent,
                    calcViewModel = calcViewModel,
                    dbState = state,
                    navController = navController,
                    sheetState = sheetState
                )

            },

            bottomBar = {
//
//
                androidx.compose.material3.BottomAppBar(
                    containerColor = AppBars,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .height(48.dp)


                ) {

//
//                    AnchoredAdaptiveBanner(
//                        modifier = Modifier,
//                        adId = adId,
//                        isLoading = state,
//                        onEvent = onEvent
//                    )

                    ShimmerBottomHomeBarItemAd(
                        isLoading = state,
                        contentAfterLoading = {
                        },
                        adId = adId,
                        onEvent = onEvent
                    )
//
                }
//
////                }
//
//
            },

            backgroundColor = Cream,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)


        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (state.enteredCourses == "0") {
                        "Click the plus button!!!"
                    } else {
                        ""
                    },
                    fontSize = 35.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    lineHeight = 1000.sp,


                    )
            }


            if (state.courseEntryDialogBoxVisibility) {
                //CardViewToDisplay(data = CourseDataEntries().coursesDataEntry)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        ) {

                        CourseEntryDialogBox(
                            onEvent = onEvent,
                            dbState = state,
                            title = "Enter your course details"
                        )

                    }

                }
            } else if (state.baseEntryDialogBoxVisibility) {


                BaseEntryDialogBox(
                    Desc = "please make your entry:",
                    state = state,
                    events = onEvent
                )

            } else if (state.editBaseEntryDialogBoxVisibility) {
                EditBaseEntryDialogBox(
                    Desc = "Edit Entry:",
                    state = state,
                    events = onEvent
                )

            } else if (state.courseEntryEditDialogBoxVisibility) {

                EditCourseEntryDialogBox(onEvent = onEvent, dbState = state, title = "Edit Entries")

            } else if (state.clearCoursesConfirmationDBoxVisibility) {

                ConfirmClearCoursesEntryConfirmationDialogBox(
                    onEvent = onEvent,
                    dbState = state,
                    sheetState = sheetState
                )


            } else {

                TotalCoursesListCardViewToDisplay(
                    data = stateTwo,
                    onClickEvent = onEvent,
                    sheetState = sheetState,
                    dbState = state
                )


            }


        }


    }


}