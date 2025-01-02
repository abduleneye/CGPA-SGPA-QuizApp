package com.eneye.enquizgpa.features.four_grading_system_cgpa_features.presentation.four_cgpa_main_screen_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eneye.enquizgpa.features.Four_grading_system_sgpa_features.presentation.FourGpaViewModel
import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFourCgpaCalculation
import com.eneye.enquizgpa.features.four_grading_system_cgpa_features.presentation.FourCgpaUiStates
import com.eneye.enquizgpa.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream
import kotlinx.coroutines.launch


@Composable
fun Init() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "No saved record(s) yet")

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UniFourSgpaRecordedResultToBeSelectedFrom(
    navController: NavController,
    fourCgpaUiStates: FourCgpaUiStates,
    fourGpaViewModel: FourGpaViewModel,
    onEventFourSgpa: (FourGpaUiEvents) -> Unit,
    sheetState: BottomSheetState,
    helperPaddingValues: PaddingValues,


    ) {


    ResultRecordToDisplay(
        data = fourCgpaUiStates,
        onEventFourSgpa = onEventFourSgpa,
        fourGpaViewModel = fourGpaViewModel,
        sheetState = sheetState,
        helperPaddingValues = helperPaddingValues


    )

}
//
//}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCardView(
    info: SgpaResultDisplayFormatForFourCgpaCalculation,
    index: Int,
    uniFourCgpaUiState: FourCgpaUiStates,
    onEventFourSgpaUiEvents: (FourGpaUiEvents) -> Unit,
    fourGpaViewModel: FourGpaViewModel,
    sheetState: BottomSheetState,


    ) {

    //val json = Gson().toJson(info.resultEntries)

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
//            .clickable {
//                Toast
//                    .makeText(myContext, "Clicked from card view!!!", Toast.LENGTH_SHORT)
//                    .show()
//            }
        ,
        colors = CardDefaults.cardColors(
            containerColor = Cream
        )


    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(100.dp)
//                .clickable {
//
//                    Toast
//                        .makeText(myContext, "Clicked from column!!!", Toast.LENGTH_SHORT)
//                        .show()
//
//
//                }
            //              ,
        ) {
            val context = LocalContext.current

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = info.resultName,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .weight(0.9f)
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 40.dp, top = 24.dp),
                )



                Checkbox(
                    colors = CheckboxDefaults.colors(
                        checkedColor = AppBars
                    ),
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(top = 0.dp, bottom = 24.dp),

                    checked = info.resultSelected,
                    onCheckedChange = {

//                        info.copy(
//                            resultSelected = it
//                        )
                        scope.launch {
                            if (sheetState.isExpanded) {
                                sheetState.collapse()
                            }
                        }
                        onEventFourSgpaUiEvents(
                            FourGpaUiEvents.onCheckChanged(
                                info = info,
                                isChecked = it,
                                index = index,
                                sgpaNeeded = info.resultSgpa,
                                resultNameRef = info.resultName
                            )
                        )
//                        Toast.makeText(
//                            context,
//                            " Sgpa for ${index}\n${info.resultName} is ${info.resultSgpa} ",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        // Invalidate()
                    },

                    )
            }


            // Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = info.resultSgpa,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 0.dp)
            )

        }

    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultRecordToDisplay(
    data: FourCgpaUiStates,
    onEventFourSgpa: (FourGpaUiEvents) -> Unit,
    fourGpaViewModel: FourGpaViewModel,
    sheetState: BottomSheetState,
    helperPaddingValues: PaddingValues,


    ) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    Text(
        text = data.helperText, fontSize = 2.sp,
        fontWeight = FontWeight.Light,
    )


    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(1024.dp)
            .padding(
                top = helperPaddingValues.calculateTopPadding(),
//                start = 16.dp,
//                end = 16.dp,

                bottom = 164.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        // contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(items = data.displayedResultForFourCgpaCalculation, key = { id, listItem ->
            id.hashCode()

        }) { index, item ->

            val context = LocalContext.current

            MyCardView(

                info = item,
                index = index,
                uniFourCgpaUiState = data,
                onEventFourSgpaUiEvents = onEventFourSgpa,
                fourGpaViewModel = fourGpaViewModel,
                sheetState = sheetState

            )

        }


    }


}