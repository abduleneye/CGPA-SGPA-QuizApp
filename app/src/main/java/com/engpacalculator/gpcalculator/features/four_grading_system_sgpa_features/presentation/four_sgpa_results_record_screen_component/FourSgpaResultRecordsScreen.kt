package com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.four_sgpa_results_record_screen_component

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.ads_components.FourScreensBottomBannerAd
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.presentation.FourGpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.entity.FourSgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component.FourSgpaResultsRecordState
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson


@Composable
fun FourSgpaInit() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cream),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "No saved sgpa record(s) yet")

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FourSgpaResultRecordScreen(
    navController: NavController,
    //state: List<FourSgpaResultEntity>,
    state: FourSgpaUiStates,
    fourSgpaResultRecordState: FourSgpaResultsRecordState,
    adId: String?,
    viewModel: FourGpaViewModel,
    onEvent: (FourGpaUiEvents) -> Unit,
    mFirebaseAnalytics: FirebaseAnalytics


) {

    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MediumTopAppBar(
                title = {
                    androidx.compose.material3.Text(text = "4.0 Sgpa Results Records")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        //navController.navigate(Screen.Home.route)
                        navController.popBackStack()
                        //navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Arrow"
                        )

                    }
                },


                )
        },
        bottomBar = {

            BottomAppBar(
                containerColor = Cream,
                contentPadding = PaddingValues(0.dp)

            ) {


                if (adId != null) {
                    FourScreensBottomBannerAd(
                        isLoading = state,
                        onEvent = onEvent,
                        contentAfterLoading = {

                        },
                        modifier = Modifier,
                        adId = adId
                    )
                }

            }


        }
    ) {


        if (fourSgpaResultRecordState.resultItems.isEmpty()) {

            FourSgpaInit()


        } else {
            FourSgpaResultRecordToDisplay(
                data = fourSgpaResultRecordState,
                navController = navController,
                onEvent = onEvent,
                viewModel = viewModel,
                helperPadder = it,
                mFirebaseAnalytics = mFirebaseAnalytics


            )

        }

    }


    //
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FourSgpaMyCardView(
    info: FourSgpaResultEntity,
    navController: NavController,
    helperPadder: PaddingValues,
    index: Int,
    modifier: Modifier = Modifier,
    state: FourSgpaUiStates,
    onEvent: (FourGpaUiEvents) -> Unit,
    viewModel: FourGpaViewModel,
    mFirebaseAnalytics: FirebaseAnalytics


) {

    val json = Gson().toJson(info.resultEntries)

    val myContext = LocalContext.current

    val scope = rememberCoroutineScope()




    Card(
        ///
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(126.dp)
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 4.dp,
                start = 16.dp,
                end = 16.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Cream
        )


    ) {

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clickable
                {

                    val params = Bundle()
                    params.putString(
                        "FourSgpaResultRecordButton",
                        "FourSgpaResultRecordButtonClicked"
                    )
                    mFirebaseAnalytics.logEvent("FourSgpaResultRecordButton", params)
                    navController.navigate(
                        Screen.Four_Sgpa_Full_Records_Screen.withArgs(
                            info.resultName,
                            json,
                            info.gp,
                            info.remark,
                            info.resultGpaDescriptor
                        )
                    )

                },
        ) {
//            Row {
//                IconButton(onClick = {
//                    onEvent(FourGpaUiEvents.DeleteResult(info))
//                    //viewModel.loadData()
//                }) {
//                    Icon(Icons.Default.Delete, contentDescription = "Delete Result")
//
//                }
//            }

            Text(
                text = info.resultName,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Text(text = info.gp, fontWeight = FontWeight.SemiBold)
            Text(text = info.remark, fontWeight = FontWeight.Bold)
            Text(text = "Tap to open", fontWeight = FontWeight.Light)


            //}

        }

    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FourSgpaResultRecordToDisplay(
    //data: List<FourSgpaResultEntity>,
    data: FourSgpaResultsRecordState,
    navController: NavController,
    onEvent: (FourGpaUiEvents) -> Unit,
    viewModel: FourGpaViewModel,
    helperPadder: PaddingValues,
    mFirebaseAnalytics: FirebaseAnalytics


) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()


    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(1024.dp)
            .background(Cream)
            .padding(
                top = helperPadder.calculateTopPadding(),
                bottom = helperPadder
                    .calculateBottomPadding()
                    .plus(16.dp)
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        // contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(items = data.resultItems, key = { id, listItem ->
            id.hashCode()
        }) { index, item ->

            val context = LocalContext.current

            FourSgpaMyCardView(

                info = item,
                index = index,
                state = FourSgpaUiStates(),
                navController = navController,
                onEvent = onEvent,
                viewModel = viewModel,
                helperPadder = helperPadder,
                mFirebaseAnalytics = mFirebaseAnalytics


            )

        }


    }


}