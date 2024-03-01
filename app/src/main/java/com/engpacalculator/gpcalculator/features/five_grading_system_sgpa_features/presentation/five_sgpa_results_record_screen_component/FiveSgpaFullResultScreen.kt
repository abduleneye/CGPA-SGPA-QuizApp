package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_results_record_screen_component

import GpCalculatorPrototype.Data.FiveGpData
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
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
fun FiveSgpaFullResultScreen(
    resultName: String?,
    actualResults: String?,
    gP: String?,
    resultRemark: String?,
    resultGpaDescriptor: String?,
    onEvent: (FiveGpaUiEvents) -> Unit,
    state: FiveSgpaUiStates,
    navController: NavController,
    adId: String?,

    ) {

    val context = LocalContext.current


    val listType = object : TypeToken<ArrayList<FiveGpData>>() {}.type
    val deserializedList: ArrayList<FiveGpData> =
        Gson().fromJson(actualResults, listType)

    //val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cream),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = "5.0 Sgpa Result Display")
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
                contentPadding = PaddingValues(0.dp),
                // modifier = Modifier.height(32.dp)

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Cream),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(0.8f)
                    //.padding(it)
                    .background(color = Cream)

            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Cream)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(
                                onClick = {

                                    var helpName: String = ""

                                    resultName?.let {
                                        FiveGpaUiEvents.DeleteFiveGpaResultByReference(
                                            it
                                        )
                                    }
                                        ?.let {
                                            onEvent(it)
                                            helpName = it.fiveSgpaResultName

                                        }
                                    Toast.makeText(
                                        context,
                                        "$helpName deleted successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.popBackStack()
                                },


                                ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete Result"
                                )

                            }
                        }
                    }


                    if (resultName != null) {
                        item {
                            Text(
                                text = resultName, modifier = Modifier
                                    .padding(top = 0.dp),
                                fontWeight = FontWeight.ExtraBold
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp)
                            ) {
                                Text(
                                    text = "course(s)",
                                    modifier = Modifier,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                    //.fillMaxHeight()
                                )
                                Text(
                                    text = "grade(s)",
                                    modifier = Modifier,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp


                                    // .fillMaxHeight()
                                )
                                Text(
                                    text = "unit(s)",
                                    modifier = Modifier,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp


                                    //.fillMaxHeight()
                                )

                            }
                        }


                    }

                    if (actualResults != null) {
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

                            ) {
                                Text(
                                    text = deserializedList[index].courseCode,
                                    modifier = Modifier,
                                    fontSize = 12.sp

                                    //  .fillMaxHeight()
                                )

                                Text(
                                    text = deserializedList[index].courseGrade,
                                    modifier = Modifier,
                                    fontSize = 12.sp

                                    // .fillMaxHeight()
                                )

                                Text(
                                    text = deserializedList[index].courseUnit.toString(),
                                    modifier = Modifier,
                                    fontSize = 12.sp

                                    // .fillMaxHeight()
                                )
                            }


                        }

                    }




                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                            // .fillMaxSize()
                            ,
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (gP != null) {
                                Text(
                                    // text = "Sgpa: $gP",
                                    modifier = Modifier.padding(),
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold

                                            )
                                        ) {
                                            append("Sgpa: ".uppercase())
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 12.sp,
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
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )

                            }
                            if (resultRemark != null) {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                        ) {
                                            append("Remark: ".uppercase())
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold

                                            )
                                        ) {
                                            append(resultRemark.uppercase())
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


    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThisPreview() {
    //FullResultScreen()
}