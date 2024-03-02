package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_main_screen_components

import GpCalculatorPrototype.Data.FourGpData
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
import com.engpacalculator.gpcalculator.core.ads_components.FourShimmerBottomHomeBarItemAd
import com.engpacalculator.gpcalculator.core.data_store.data_store_repo.FourCgpaIntroDataStoreRepoVisibility
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.presentation.FourGpaViewModel
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.FourCgpaIntroDialogBoxFromDatatStore
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.FourCgpaIntroDialogBoxFromViewModel
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.FourCgpaUiStates
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_results_record_screen_component.FourCgpaSaveResultDialogBox
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FourCgpaMainScreen(
    onEvent: (FourGpaUiEvents) -> Unit,
    fourSgpaUiStates: FourSgpaUiStates,
    fourCgpaHelperRecordsState: FourCgpaUiStates,
    fourCgpaUiStatesFromSgpaViewModel: FourCgpaUiStates,
    fourCgpaUiStates: FourCgpaUiStates,

    stateTwo: ArrayList<FourGpData>,
    navController: NavController,
    adId: String,
    //uniFourCgpaViewModel: FourCgpaViewModel,
    fourGpaViewModel: FourGpaViewModel,
    mFirebaseAnalytics: FirebaseAnalytics


    // onEventFourCgpa: (FourCgpaUiEvents) -> Unit

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
    var initial_working_StatusIcon =
        // if (fourCgpaUiStatesFromSgpaViewModel.operatorIconState == true) {
        Icons.Default.Done

//        } else {
//            Icons.Default.Add
//
//
//        }


    var finalStatusIcon = Icons.Filled.Done



    BottomSheetScaffold(


        scaffoldState = scaffoldState,
        sheetContent = {
            FourCgpaResultBottomSheetContent(
                fourSgpaUiStates = fourSgpaUiStates,
                fourCgpaUiStates = fourCgpaUiStatesFromSgpaViewModel,
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

                        onEvent(FourGpaUiEvents.helpFourCgpa)
                        onEvent(FourGpaUiEvents.executeCgpaCalculation)
                        if (fourCgpaUiStatesFromSgpaViewModel.sgpaListToBeCalculated.isNotEmpty()) {
                            scope.launch {
                                if (sheetState.isCollapsed
                                ) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        } else if (fourCgpaHelperRecordsState.displayedResultForFourCgpaCalculation.isEmpty()) {
                            onEvent(FourGpaUiEvents.showFourCgpaIntroDialogBox)
//                            Toast.makeText(
//                                context,
//                                "Please go back and saveFourCgpaResult an sgpa result",
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

                FourCgpaTopAppBarAndOptionsMenu(
                    onEvent = onEvent,
                    calcViewModel = fourGpaViewModel,
                    dbState = fourCgpaUiStates,
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
                    FourShimmerBottomHomeBarItemAd(
                        isLoading = fourSgpaUiStates,
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

            val dataStore = FourCgpaIntroDataStoreRepoVisibility(context = context)
            val status =
                dataStore.getFourCgpaIntroDialogBoxVisibilityStatus.collectAsState(initial = false).value


            Box {
                Column {
                    UniFourSgpaRecordedResultToBeSelectedFrom(
                        navController = navController,
                        fourCgpaUiStates = fourCgpaHelperRecordsState,
                        fourGpaViewModel = fourGpaViewModel,
                        onEventFourSgpa = onEvent,
                        sheetState = sheetState,
                        helperPaddingValues = it
                    )


                }

                if (fourCgpaUiStates.saveResultDBVisibilty) {

                    FourCgpaSaveResultDialogBox(
                        onEvent = onEvent,
                        fourCgpaUiStates = fourCgpaUiStates,
                        sheetState = sheetState,
                        mFirebaseAnalytics = mFirebaseAnalytics

                    )

                }
                if (fourCgpaHelperRecordsState.displayedResultForFourCgpaCalculation.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(text = "No saved SGPA record(s) found")

                    }
                }

                if (fourCgpaUiStates.fourCgpaIntroDialogBoxVisibility) {
                    FourCgpaIntroDialogBoxFromViewModel(
                        FourCgpaUiEvents = onEvent,
                        FourCgpaUiStates = fourCgpaUiStates
                    )
                }

                if (status) {
                    FourCgpaIntroDialogBoxFromDatatStore()
                }


            }


        }


    }
}

