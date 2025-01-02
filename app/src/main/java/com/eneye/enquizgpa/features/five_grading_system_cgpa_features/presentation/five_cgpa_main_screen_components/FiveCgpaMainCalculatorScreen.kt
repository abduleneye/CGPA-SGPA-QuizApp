package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.five_cgpa_main_screen_components

import GpCalculatorPrototype.Data.FiveGpData
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eneye.enquizgpa.core.ads_components.FiveShimmerBottomHomeBarItemAd
import com.eneye.enquizgpa.core.data_store.data_store_repo.FiveCgpaIntroDataStoreRepoVisibility
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.FiveCgpaIntroDialogBoxFromDatatStore
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.FiveCgpaIntroDialogBoxFromViewModel
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component.FiveCgpaSaveResultDialogBox
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FiveCgpaMainScreen(
    onEvent: (FiveGpaUiEvents) -> Unit,
    fiveSgpaUiStates: FiveSgpaUiStates,
    fiveCgpaHelperRecordsState: FiveCgpaUiStates,
    fiveCgpaUiStatesFromSgpaViewModel: FiveCgpaUiStates,
    fiveCgpaUiStates: FiveCgpaUiStates,

    stateTwo: ArrayList<FiveGpData>,
    navController: NavController,
    adId: String,
    //uniFiveCgpaViewModel: FiveCgpaViewModel,
    fiveGpaViewModel: FiveGpaViewModel,
    mFirebaseAnalytics: FirebaseAnalytics,


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
    var initial_working_StatusIcon = Icons.Default.Done
    val dataStore = FiveCgpaIntroDataStoreRepoVisibility(context = context)
    val status =
        dataStore.getFiveCgpaIntroDialogBoxVisibilityStatus.collectAsState(initial = false).value



    BottomSheetScaffold(


        scaffoldState = scaffoldState,
        sheetContent = {
            FiveCgpaResultBottomSheetContent(
                fiveSgpaUiStates = fiveSgpaUiStates,
                fiveCgpaUiStates = fiveCgpaUiStatesFromSgpaViewModel,
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

                        onEvent(FiveGpaUiEvents.helpFiveCgpa)
                        onEvent(FiveGpaUiEvents.executeCgpaCalculation)
                        if (fiveCgpaUiStatesFromSgpaViewModel.sgpaListToBeCalculated.isNotEmpty()) {
                            scope.launch {
                                if (sheetState.isCollapsed
                                ) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        } else if (fiveCgpaHelperRecordsState.displayedResultForFiveCgpaCalculation.isEmpty()) {
                            onEvent(FiveGpaUiEvents.showFiveCgpaIntroDialogBox)
//                            Toast.makeText(
//                                context,
//                                "Please go back and save an sgpa result",
//                                Toast.LENGTH_SHORT
//                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Please check a result box to calculate",
                                Toast.LENGTH_SHORT
                            ).show()
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

                FiveCgpaTopAppBarAndOptionsMenu(
                    onEvent = onEvent,
                    calcViewModel = fiveGpaViewModel,
                    dbState = fiveCgpaUiStates,
                    navController = navController,
                    sheetState = sheetState,
                    mFirebaseAnalytics = mFirebaseAnalytics
                )

            },

            bottomBar = {

                BottomAppBar(
                    containerColor = Cream,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier


                ) {
                    FiveShimmerBottomHomeBarItemAd(
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

            if (status) {

                FiveCgpaIntroDialogBoxFromDatatStore()

            }

            if (fiveCgpaUiStates.fiveCgpaIntroDialogBoxVisibility == true) {
                FiveCgpaIntroDialogBoxFromViewModel(
                    fiveCgpaUiEvents = onEvent,
                    fiveCgpaUiStates = fiveCgpaUiStates
                )

            }


            Box {
                Column {
                    UniFiveSgpaRecordedResultToBeSelectedFrom(
                        navController = navController,
                        fiveCgpaUiStates = fiveCgpaHelperRecordsState,
                        fiveGpaViewModel = fiveGpaViewModel,
                        onEventFiveSgpa = onEvent,
                        sheetState = sheetState,
                        helperPaddingValues = it
                    )


                }

                if (fiveCgpaUiStates.saveResultDBVisibilty == true) {

                    FiveCgpaSaveResultDialogBox(
                        onEvent = onEvent,
                        fiveCgpaUiStates = fiveCgpaUiStates,
                        sheetState = sheetState,
                        mFirebaseAnalytics = mFirebaseAnalytics

                    )

                }
                if (fiveCgpaHelperRecordsState.displayedResultForFiveCgpaCalculation.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(text = "No saved SGPA record(s) found")

                    }
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

