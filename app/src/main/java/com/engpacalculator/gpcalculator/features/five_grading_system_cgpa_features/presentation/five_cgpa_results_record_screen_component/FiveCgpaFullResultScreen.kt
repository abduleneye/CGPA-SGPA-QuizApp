package com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.presentation.five_cgpa_results_record_screen_component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import com.engpacalculator.gpcalculator.core.ads_components.FiveScreensBottomBannerAd
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiveCgpaFullResultScreen(
    resultName: String?,
    actualResults: String?,
    gP: String?,
    resultRemark: String?,
    resultGpaDescriptor: String?,
    onEvent: (FiveGpaUiEvents) -> Unit,
    navController: NavController,
    state: FiveSgpaUiStates,
    adId: String?,

    ) {

    val context = LocalContext.current


    val listType = object : TypeToken<ArrayList<String>>() {}.type
    val deserializedList: ArrayList<String> =
        Gson().fromJson(actualResults, listType)

    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cream),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "5.0 Cgpa Result Display")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                //.background(color = Color.Red),
                .background(color = Cream),
            verticalArrangement = Arrangement.Center

        ) {

            item {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Cream),

                    ) {

                    ///


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Cream),
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
                                .height(600.dp)
                                .width(350.dp)
                                .background(color = Cream)
                                .padding(it)

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
                                    .background(color = Cream)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    IconButton(onClick = {

                                        var helpName: String = ""


                                        resultName?.let {
                                            FiveGpaUiEvents.DeleteFiveCgpaResultByReference(
                                                it
                                            )
                                        }
                                            ?.let {
                                                onEvent(it)
                                                helpName = it.fiveCgpaResultName
                                            }
                                        //viewModel.loadData()
                                        //navController.navigate(Screen.Five_Sgpa_Records_Screen.route)
                                        Toast.makeText(
                                            context,
                                            "$helpName deleted successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        navController.popBackStack()
                                    }
                                    ) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Delete Result"
                                        )

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
                                        text = "CGPA Calculated for".uppercase(),
                                        modifier = Modifier,
                                        fontWeight = FontWeight.Bold
                                        //.fillMaxHeight()
                                    )
//                        Text(
//                            text = "Course grades",
//                            modifier = Modifier,
//                            fontWeight = FontWeight.Bold
//
//                            // .fillMaxHeight()
//                        )
//                        Text(
//                            text = "Course units",
//                            modifier = Modifier,
//                            fontWeight = FontWeight.Bold
//
//                            //.fillMaxHeight()
//                        )

                                }

                                Box(
                                    modifier = Modifier
                                        .background(color = Cream)
                                        .padding(top = 0.dp, bottom = 0.dp)
                                    //.background(color = Color.Red)
                                ) {

                                    if (actualResults != null) {


                                        LazyColumn(
                                            state = lazyListState,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                //.background(color = Color.Green)
                                                .padding(bottom = 0.dp),

                                            // .height(1024.dp),
                                            verticalArrangement = Arrangement.SpaceAround,
                                        ) {


                                            itemsIndexed(
                                                items = deserializedList,
                                                key = { id, listItem ->
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
                                                        text = deserializedList[index],
                                                        modifier = Modifier
                                                        //  .fillMaxHeight()
                                                    )

//                                        Text(
//                                            text = deserializedList[index].courseGrade,
//                                            modifier = Modifier
//                                            // .fillMaxHeight()
//                                        )
//
//                                        Text(
//                                            text = deserializedList[index].courseUnit.toString(),
//                                            modifier = Modifier
//                                            // .fillMaxHeight()
//                                        )
                                                }


                                            }


                                        }


                                    }

                                }


                                if (gP != null) {
                                    Text(
                                        // text = "Sgpa: $gP",
                                        modifier = Modifier.padding(it),
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold

                                                )
                                            ) {
                                                append("CGPA: ")
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
                                if (resultGpaDescriptor != null) {

                                    Text(
                                        text = resultGpaDescriptor.uppercase(),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
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
                                                append("Remark: ".uppercase())
                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold

                                                )
                                            ) {
                                                append(resultRemark.uppercase())
                                            }

                                        },
                                        modifier = Modifier.padding(bottom = 64.dp)
                                    )
                                }

                            }
                        }


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