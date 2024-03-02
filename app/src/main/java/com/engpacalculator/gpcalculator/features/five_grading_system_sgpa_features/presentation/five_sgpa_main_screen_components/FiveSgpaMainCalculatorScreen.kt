package com.engpacalculator.gpcalculator.presentation.myViewModels.course_list_screen_component

import GpCalculatorPrototype.Data.FiveGpData
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.ads_components.FiveShimmerBottomHomeBarItemAd
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components.FiveSgpaBaseEntryDialogBox
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components.FiveSgpaConfirmClearCoursesEntryConfirmationDialogBox
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components.FiveSgpaCourseDetailsEntryDialogBox
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components.FiveSgpaEditBaseEntryDialogBox
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components.FiveSgpaResultBottomSheetContent
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components.FiveSgpaTopAppBarAndOptionsMenu
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FiveSgpaSaveResultDialogBox
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FiveSgpaMainScreen(
    onEvent: (FiveGpaUiEvents) -> Unit,
    state: FiveSgpaUiStates,
    stateTwo: ArrayList<FiveGpData>,
    calcViewModel: FiveGpaViewModel,
    navController: NavController,
    adId: String,
    mFirebaseAnalytics: FirebaseAnalytics

) {

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
    val sheetHeight = remember {
        mutableStateOf(500.dp)
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
        sheetContent = { FiveSgpaResultBottomSheetContent(state, sheetState, onEvent = onEvent) },
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .width(sheetWidth.value)
//                .height(sheetHeight.value),
            .background(color = Cream),

        sheetGesturesEnabled = true,
        sheetPeekHeight = 0.dp,
        drawerGesturesEnabled = true,
        sheetElevation = 100.dp,
        backgroundColor = Cream,

        ) {

        Scaffold(

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

//                        scope.launch {
//
//                            scaffoldState.snackbarHostState.showSnackbar(
//                                "${state.saveResultAs} saved in sgpa records successfully",
//                                null,
//                                duration = SnackbarDuration.Indefinite
//                            )
//
//
//                        }


                        if (state.totalCourses == ""
                        //|| state.totalCreditLoad == ""
                        ) {

                            onEvent(FiveGpaUiEvents.showBaseEntryDBox)

                        } else if (stateTwo.size < state.totalCourses.toInt()) {

                            onEvent(FiveGpaUiEvents.showDataEntryDBox)
//                            Toast.makeText(
//                                context,
//                                "list  size: ${stateTwo.size} TONOEC: ${state.totalCourses}",
//                                Toast.LENGTH_LONG
//                            ).show()


//                    } else if (state.totalCreditLoad == "" || state.totalCourses == "") {
//                        onEvent(FiveGpaUiEvents.showBaseEntryDBox)

                        } else {
                            onEvent(FiveGpaUiEvents.executeCalculation)

                            //onEvent(FiveGpaUiEvents.showResultDBox)
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

                    Icon(
                        imageVector = initial_working_StatusIcon,
                        contentDescription = "Add Course details",


                        )


                }
            },
            topBar = {

                FiveSgpaTopAppBarAndOptionsMenu(
                    onEvent = onEvent,
                    calcViewModel = calcViewModel,
                    dbState = state,
                    navController = navController,
                    sheetState = sheetState,
                    mFirebaseAnalytics = mFirebaseAnalytics
                )

            },

            bottomBar = {
//
//
                androidx.compose.material3.BottomAppBar(
                    containerColor = Cream,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                    // .height(48.dp)
                    // .fillMaxWidth()


                ) {

//
//                    AnchoredAdaptiveBanner(
//                        modifier = Modifier,
//                        adId = adId,
//                        isLoading = state,
//                        onEvent = onEvent
//                    )

                    FiveShimmerBottomHomeBarItemAd(
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
            modifier = Modifier//  .padding(it)


        ) {

            var style = TextStyle(fontSize = 35.sp)

            var resizedTextStyle by remember {
                mutableStateOf(style)

            }


            if (state.enteredCourses == "0") {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {


                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            // modifier = Modifier.fillMaxSize(),
                            text = "Click the plus button!!!",
                            //fontSize = 35.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            lineHeight = 1000.sp,
                            softWrap = false,
                            onTextLayout = { result ->
                                if (result.didOverflowWidth) {
                                    resizedTextStyle = resizedTextStyle.copy(
                                        fontSize = resizedTextStyle.fontSize * 0.95
                                    )

                                }

                            },
                            style = resizedTextStyle,
                            //  textAlign = Alignment.CenterVertically


                        )
                    }
                }

            }




            if (state.courseEntryDialogBoxVisibility) {
                //CardViewToDisplay(data = FiveCourseDataEntries().coursesDataEntry)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        ) {

                        FiveSgpaCourseDetailsEntryDialogBox(
                            onEvent = onEvent,
                            dbState = state,
                            title = "Enter your course details"
                        )

                    }

                }
            } else if (state.baseEntryDialogBoxVisibility) {


                FiveSgpaBaseEntryDialogBox(
                    Desc = "please make your entry:",
                    state = state,
                    events = onEvent
                )

            } else if (state.editBaseEntryDialogBoxVisibility) {
                FiveSgpaEditBaseEntryDialogBox(
                    Desc = "Edit Entry:",
                    state = state,
                    events = onEvent
                )

            } else if (state.courseEntryEditDialogBoxVisibility) {

                FiveSgpaEditCourseEntryDialogBox(
                    onEvent = onEvent,
                    dbState = state,
                    title = "Edit Entries"
                )

            } else if (state.clearCoursesConfirmationDBoxVisibility) {

                FiveSgpaConfirmClearCoursesEntryConfirmationDialogBox(
                    onEvent = onEvent,
                    dbState = state,
                    sheetState = sheetState
                )


            } else if (state.saveResultAsDialogBoxVisibility) {
                FiveSgpaSaveResultDialogBox(
                    onEvent = onEvent,
                    dbState = state,
                    sheetState = sheetState,
                    mFirebaseAnalytics = mFirebaseAnalytics
                )
            } else {


                FiveSgpaTotalCoursesListCardViewToDisplay(
                    data = stateTwo,
                    onClickEvent = onEvent,
                    sheetState = sheetState,
                    dbState = state,
                    helperPaddingValues = it

                )


            }


        }


    }


}