package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component

import GpCalculatorPrototype.Data.GpData
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiEvents
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Composable
fun FiveSgpaFullResultScreen(
    resultName: String?,
    actualResults: String?,
    gP: String?,
    resultRemark: String?,
    onEvent: (FiveSgpaUiEvents) -> Unit,
    navController: NavController
) {

    val listType = object : TypeToken<ArrayList<GpData>>() {}.type
    val deserializedList: ArrayList<GpData> =
        Gson().fromJson(actualResults, listType)

    val state = rememberLazyListState()


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {

        ///
        var scrollState = rememberScrollState()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState),
//                .padding(
//                    top = it
//                        .calculateTopPadding()
//                        .plus(20.dp),
//                    bottom = it
//                        .calculateBottomPadding()
//                        .plus(20.dp)
//                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ///

            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .height(650.dp)
                    .width(350.dp)

            ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ){

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = {
                            resultName?.let { FiveSgpaUiEvents.DeleteResultByReference(it) }
                                ?.let { onEvent(it) }
                            //viewModel.loadData()
                            //navController.navigate(Screen.Five_Sgpa_Records_Screen.route)
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete Result")

                        }
                    }

                    if (resultName != null) {
                        Text(
                            text = resultName, modifier = Modifier
                                .padding(top = 0.dp),
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                    ) {
                        Text(
                            text = "Course codes",
                            modifier = Modifier,
                            fontWeight = FontWeight.Bold
                            //.fillMaxHeight()
                        )
                        Text(
                            text = "Course grades",
                            modifier = Modifier,
                            fontWeight = FontWeight.Bold

                            // .fillMaxHeight()
                        )
                        Text(
                            text = "Course units",
                            modifier = Modifier,
                            fontWeight = FontWeight.Bold

                            //.fillMaxHeight()
                        )

                    }

                    Box(
                        modifier = Modifier
                            //.height(250.dp)
                            .padding(top = 0.dp, bottom = 0.dp)
                        //.background(color = Color.Red)
                    ) {

                        if (actualResults != null) {


                            LazyColumn(
                                state = state,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    //.background(color = Color.Green)
                                    .padding(bottom = 0.dp),

                                // .height(1024.dp),
                                verticalArrangement = Arrangement.SpaceAround,
                            ) {


                                itemsIndexed(items = deserializedList, key = { id, listItem ->
                                    id.hashCode()
                                }) { index, item ->

                                    val context = LocalContext.current

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceAround,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 16.dp, end = 16.dp)
                                        //    .background(color = Color.Cyan)
                                        // .padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),
                                    ) {
                                        Text(
                                            text = deserializedList[index].courseCode,
                                            modifier = Modifier
                                            //  .fillMaxHeight()
                                        )

                                        Text(
                                            text = deserializedList[index].courseGrade,
                                            modifier = Modifier
                                            // .fillMaxHeight()
                                        )

                                        Text(
                                            text = deserializedList[index].courseUnit.toString(),
                                            modifier = Modifier
                                            // .fillMaxHeight()
                                        )
                                    }


                                }


                            }


                        }

                    }


                    if (gP != null) {
                        Text(
                            // text = "Sgpa: $gP",
                            modifier = Modifier.padding(),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold

                                    )
                                ) {
                                    append("Sgpa: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                ) {
                                    append(gP)
                                }
                            },
                        )
                    }
                    if (resultRemark != null) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                ) {
                                    append("Remark: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold

                                    )
                                ) {
                                    append(resultRemark)
                                }

                            },
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                    }

                }
            }


        }


    }

}

//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThisPreview() {
    //FullResultScreen()
}