package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_main_screen_components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.SgpaResultDisplayFormatForFiveCgpaCalculation
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiEvents
import com.engpacalculator.gpcalculator.ui.theme.Cream
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
fun UniFiveSgpaRecordedResultToBeSelectedFrom(
    navController: NavController,
    fiveCgpaUiStates: FiveCgpaUiStates,
    fiveGpaViewModel: FiveGpaViewModel,
    onEventFiveSgpa: (FiveSgpaUiEvents) -> Unit,
    sheetState: BottomSheetState


) {


    ResultRecordToDisplay(
        data = fiveCgpaUiStates,
        onEventFiveSgpa = onEventFiveSgpa,
        fiveGpaViewModel = fiveGpaViewModel,
        sheetState = sheetState


    )

}
//
//}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyCardView(
    info: SgpaResultDisplayFormatForFiveCgpaCalculation,
    index: Int,
    uniFiveCgpaUiState: FiveCgpaUiStates,
    onEventFiveSgpaUiEvents: (FiveSgpaUiEvents) -> Unit,
    fiveGpaViewModel: FiveGpaViewModel,
    sheetState: BottomSheetState


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
                .height(100.dp)
                .clickable {

                    Toast
                        .makeText(myContext, "Clicked from column!!!", Toast.LENGTH_SHORT)
                        .show()


                },
        ) {
            val context = LocalContext.current

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {


                Checkbox(
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Red
                    ),
                    checked = info.resultSelected,
                    onCheckedChange = {

                        scope.launch {
                            if (sheetState.isExpanded) {
                                sheetState.collapse()
                            }
                        }
                        onEventFiveSgpaUiEvents(
                            FiveSgpaUiEvents.onCheckChanged(
                                info = info,
                                isChecked = it,
                                index = index,
                                sgpaNeeded = info.resultSgpa,
                                resultNameRef = info.resultName
                            )
                        )
                        Toast.makeText(
                            context,
                            " Sgpa for ${index} ${info.resultName} is ${info.resultSgpa} ",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Invalidate()
                    },

                    )
            }



            Text(text = info.resultName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = info.resultSgpa, fontWeight = FontWeight.SemiBold)

        }

    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultRecordToDisplay(
    data: FiveCgpaUiStates,
    onEventFiveSgpa: (FiveSgpaUiEvents) -> Unit,
    fiveGpaViewModel: FiveGpaViewModel,
    sheetState: BottomSheetState


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
            .height(1024.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        // contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(items = data.displayedResultForFiveCgpaCalculation, key = { id, listItem ->
            id.hashCode()

        }) { index, item ->

            val context = LocalContext.current

            MyCardView(

                info = item,
                index = index,
                uniFiveCgpaUiState = data,
                onEventFiveSgpaUiEvents = onEventFiveSgpa,
                fiveGpaViewModel = fiveGpaViewModel,
                sheetState = sheetState

            )

        }


    }


}