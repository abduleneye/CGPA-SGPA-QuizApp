package com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.presentation.four_cgpa_results_record_screen_component

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.ads_components.FourScreensBottomBannerAd
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourGpaUiEvents
import com.engpacalculator.gpcalculator.features.four_grading_system_sgpa_features.presentation.FourSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FourCgpaFullResultScreen(
    resultName: String?,
    actualResults: String?,
    gP: String?,
    resultRemark: String?,
    resultGpaDescriptor: String?,
    onEvent: (FourGpaUiEvents) -> Unit,
    navController: NavController,
    state: FourSgpaUiStates,
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


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Cream)
                .padding(
                    start = it.calculateStartPadding(LayoutDirection.Rtl),
                    end = it.calculateStartPadding(LayoutDirection.Rtl)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            ///

            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxHeight(0.95f)
                    .fillMaxWidth(0.8f)
                    .padding(it)
                    .background(color = Cream)
            ) {
//

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
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
                                FourGpaUiEvents.DeleteFourCgpaResultByReference(
                                    it
                                )
                            }
                                ?.let {
                                    onEvent(it)
                                    helpName = it.fourCgpaResultName
                                }
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
                        )

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
                                    ) {
                                        Text(
                                            text = deserializedList[index],
                                            modifier = Modifier
                                        )

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


    }


}

//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThisPreview() {
    //FullResultScreen()
}