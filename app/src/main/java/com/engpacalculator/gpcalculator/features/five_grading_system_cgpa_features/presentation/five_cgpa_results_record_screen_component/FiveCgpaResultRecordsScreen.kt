package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.entity.FiveCgpaResultEntity
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiEvents
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.gson.Gson


@Composable
fun Init() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "No saved  cgpa  record(s) yet")

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FiveCgpaResultRecordScreen(
    navController: NavController,
    //state: List<FiveSgpaResultEntity>,
    state: FiveCgpaResultsRecordState,

    viewModel: FiveGpaViewModel,
    onEvent: (FiveSgpaUiEvents) -> Unit

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


    if (state.resultItems.isEmpty()) {

        Init()


    } else {
        ResultRecordToDisplay(
            data = state,
            navController = navController,
            onEvent = onEvent,
            viewModel = viewModel
        )

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
    onEvent: (FiveSgpaUiEvents) -> Unit,
    viewModel: FiveGpaViewModel,


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
                        Screen.Five_Sgpa_Full_Records_Screen.withArgs(
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
//                    onEvent(FiveSgpaUiEvents.DeleteResult(info))
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
fun ResultRecordToDisplay(
    //data: List<FiveSgpaResultEntity>,
    data: FiveCgpaResultsRecordState,
    navController: NavController,
    onEvent: (FiveSgpaUiEvents) -> Unit,
    viewModel: FiveGpaViewModel,

    ) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()


    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(1024.dp),
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
                viewModel = viewModel

            )

        }


    }


}