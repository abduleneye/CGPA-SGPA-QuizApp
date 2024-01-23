package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_results_record_screen_component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.ads_components.FourScreensBottomBannerAd
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.presentation.FourGpaViewModel
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.local.entity.FourCgpaResultEntity
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.FourCgpaUiStates
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
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
fun FourCgpaResultRecordScreen(
    navController: NavController,
    state: FourSgpaUiStates,
    fourCgpaResultRecordState: FourCgpaResultsRecordState,
    viewModel: FourGpaViewModel,
    onEvent: (FourGpaUiEvents) -> Unit,
    adId: String?,


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
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material3.Text(text = "4.0 Cgpa Results Records")
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

        if (fourCgpaResultRecordState.resultItems.isEmpty()) {

            Init()


        } else {
            FiveCgpaResultRecordToDisplay(
                data = fourCgpaResultRecordState,
                navController = navController,
                onEvent = onEvent,
                viewModel = viewModel,
                helperPadder = it
            )

        }

    }


    //
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCardView(
    info: FourCgpaResultEntity,
    navController: NavController,
    index: Int,
    modifier: Modifier = Modifier,
    state: FourCgpaUiStates,
    onEvent: (FourGpaUiEvents) -> Unit,
    viewModel: FourGpaViewModel,


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
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                Toast
                    .makeText(myContext, "Clicked from card view!!!", Toast.LENGTH_SHORT)
                    .show()
            },
        colors = CardDefaults.cardColors(
            containerColor = Cream
        )


    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate(
                        Screen.Four_Cgpa_Full_Records_Screen.withArgs(
                            info.resultName,
                            json,
                            info.gp,
                            info.remark
                        )
                    )
                    Toast
                        .makeText(myContext, "Clicked from column!!!", Toast.LENGTH_SHORT)
                        .show()


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

            Text(text = info.resultName, fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = info.resultEntries.toString(), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = info.gp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = info.remark, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "Tap to open", fontWeight = FontWeight.Light)


            //}

        }

    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FiveCgpaResultRecordToDisplay(
    //data: List<FourSgpaResultEntity>,
    data: FourCgpaResultsRecordState,
    navController: NavController,
    onEvent: (FourGpaUiEvents) -> Unit,
    viewModel: FourGpaViewModel,
    helperPadder: PaddingValues


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
                state = FourCgpaUiStates(),
                navController = navController,
                onEvent = onEvent,
                viewModel = viewModel

            )

        }


    }


}