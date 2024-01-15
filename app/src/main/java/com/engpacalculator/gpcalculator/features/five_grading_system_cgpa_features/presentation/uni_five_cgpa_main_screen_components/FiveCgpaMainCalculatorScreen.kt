package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.uni_five_cgpa_main_screen_components

import GpCalculatorPrototype.Data.GpData
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.ads_components.ShimmerBottomHomeBarItemAd
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FiveSgpaResultsRecordState
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FiveCgpaMainScreen(
    onEvent: (FiveSgpaUiEvents) -> Unit,

    fiveSgpaUiStates: FiveSgpaUiStates,
    fiveSgpaRecordsState: FiveSgpaResultsRecordState,
    fiveCgpaUiStates: FiveCgpaUiStates,
    stateTwo: ArrayList<GpData>,
    navController: NavController,
    adId: String,
    //uniFiveCgpaViewModel: FiveCgpaViewModel,
    fiveSgpaViewModel: FiveSgpaViewModel,
    // onEventFiveCgpa: (FiveCgpaUiEvents) -> Unit

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
    var initial_working_StatusIcon = Icons.Filled.Add


    var finalStatusIcon = Icons.Filled.Done



    BottomSheetScaffold(


        scaffoldState = scaffoldState,
        sheetContent = {
            FiveCgpaResultBottomSheetContent(
                fiveSgpaUiStates = fiveSgpaUiStates,
                fiveCgpaUiStates = fiveCgpaUiStates,
                sheetState = sheetState,
                onEvent = onEvent,
            )

        },
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .width(sheetWidth.value)
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


                        if (fiveCgpaUiStates.sgpaListToBeCalculated.isEmpty()
                        //|| state.totalCreditLoad == ""
                        ) {
                            initial_working_StatusIcon = Icons.Default.Add


                        } else if (fiveCgpaUiStates.sgpaListToBeCalculated.isNotEmpty()) {
                            initial_working_StatusIcon = Icons.Default.Done


                            onEvent(FiveSgpaUiEvents.executeCgpaCalculation)
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

                FiveCgpaTopAppBarDropDownMenu(
                    onEvent = onEvent,
                    calcViewModel = fiveSgpaViewModel,
                    dbState = fiveSgpaUiStates,
                    navController = navController,
                    sheetState = sheetState
                )

            },

            bottomBar = {

                BottomAppBar(
                    containerColor = Cream,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier


                ) {
                    ShimmerBottomHomeBarItemAd(
                        isLoading = fiveSgpaUiStates,
                        contentAfterLoading = {
                        },
                        adId = adId,
                        onEvent = onEvent
                    )

                }

            },

            backgroundColor = Cream,
            modifier = Modifier//  .padding(it)


        ) {

            val chkBoxState = remember {
                mutableStateOf(false)
            }


            Box {
                Column {
                    UniFiveSgpaRecordedResultToBeSelectedFrom(
                        navController = navController,
                        fiveCgpaUiStates = fiveCgpaUiStates,
                        fiveSgpaViewModel = fiveSgpaViewModel,
                        onEventFiveSgpa = onEvent,
                    )

                }


            }


//            var style = TextStyle(fontSize = 35.sp)
//
//            var resizedTextStyle by remember {
//                mutableStateOf(style)
//
//            }
//
//
//            if (state.enteredCourses == "0") {
//
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//
//
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth(0.9f),
//                        contentAlignment = Alignment.Center
//
//                    ) {
//                        Text(
//                            // modifier = Modifier.fillMaxSize(),
//                            text = "Click the plus button!!!",
//                            //fontSize = 35.sp,
//                            color = Color.Gray,
//                            maxLines = 1,
//                            lineHeight = 1000.sp,
//                            softWrap = false,
//                            onTextLayout = { result ->
//                                if (result.didOverflowWidth) {
//                                    resizedTextStyle = resizedTextStyle.copy(
//                                        fontSize = resizedTextStyle.fontSize * 0.95
//                                    )
//
//                                }
//
//                            },
//                            style = resizedTextStyle,
//                            //  textAlign = Alignment.CenterVertically
//
//
//                        )
//                    }
//                }
//
//            }


        }


    }

}


