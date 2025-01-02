package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component

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
import com.eneye.enquizgpa.core.ads_components.FiveScreensBottomBannerAd
import com.eneye.enquizgpa.core.navigation.Screen
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson


@Composable
fun Init() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cream),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "No saved  cgpa  record(s) yet")

    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FiveCgpaResultRecordScreen(
    navController: NavController,
    state: FiveSgpaUiStates,
    fiveCgpaResultRecordState: FiveCgpaResultsRecordState,
    viewModel: FiveGpaViewModel,
    onEvent: (FiveGpaUiEvents) -> Unit,
    adId: String?,
    mFirebaseAnalytics: FirebaseAnalytics,


    ) {

    val scope = rememberCoroutineScope()


//    LaunchedEffect(key1 = true) {
//
//        scope.launch {
//            viewModel.loadData()
//
//        }
//
//
//    }
    //


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MediumTopAppBar(
                title = {
                    androidx.compose.material3.Text(text = "5.0 Cgpa Results Records")
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
                    FiveScreensBottomBannerAd(
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

        if (fiveCgpaResultRecordState.resultItems.isEmpty()) {

            Init()


        } else {
            ResultRecordToDisplay(
                data = fiveCgpaResultRecordState,
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
fun MyCardView(
    info: FiveCgpaResultEntity,
    navController: NavController,
    index: Int,
    modifier: Modifier = Modifier,
    state: FiveCgpaUiStates,
    onEvent: (FiveGpaUiEvents) -> Unit,
    viewModel: FiveGpaViewModel,
    mFirebaseAnalytics: FirebaseAnalytics,


    ) {

    val json = Gson().toJson(info.resultEntries)

    val myContext = LocalContext.current

    val scope = rememberCoroutineScope()




    Card(
        ///
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Cream
        )


    ) {

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clickable {

                    val params = Bundle()
                    params.putString(
                        "FiveCgpaResultRecordButton",
                        "FiveCgpaResultRecordButtonClicked"
                    )
                    mFirebaseAnalytics.logEvent("FiveCgpaResultRecordButton", params)

                    navController.navigate(
                        Screen.Five_Cgpa_Full_Records_Screen.withArgs(
                            info.resultName,
                            json,
                            info.gp,
                            info.remark,
                            info.resultGpaDescriptor
                        )
                    )
//                    Toast
//                        .makeText(myContext, "Clicked from column!!!", Toast.LENGTH_SHORT)
//                        .show()


                },
        ) {
//            Row {
//                IconButton(onClick = {
//                    onEvent(FiveGpaUiEvents.DeleteResult(info))
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
fun ResultRecordToDisplay(
    //data: List<FourSgpaResultEntity>,
    data: FiveCgpaResultsRecordState,
    navController: NavController,
    onEvent: (FiveGpaUiEvents) -> Unit,
    viewModel: FiveGpaViewModel,
    helperPadder: PaddingValues,
    mFirebaseAnalytics: FirebaseAnalytics,


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

            MyCardView(

                info = item,
                index = index,
                state = FiveCgpaUiStates(),
                navController = navController,
                onEvent = onEvent,
                viewModel = viewModel,
                mFirebaseAnalytics = mFirebaseAnalytics


            )

        }


    }


}