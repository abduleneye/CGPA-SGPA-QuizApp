package com.engpacalculator.gpcalculator.four_grading_system_top_level_components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.DefaultCardSample
import com.engpacalculator.gpcalculator.core.ads_components.ShimmerBottomAboutBarItemAd
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Four_Grading_System_Mode_Screen(

    navController: NavController?,
    adId: String?,
    state: FiveSgpaUiStates?,
    onEvent: ((FiveGpaUiEvents) -> Unit)?

) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "4.0 Calculator mode")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),

                navigationIcon = {
                    IconButton(onClick = {
                        //navController.navigate(Screen.Home.route)
                        //navController.popBackStack()
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


                if (onEvent != null) {
                    if (state != null) {
                        if (adId != null) {
                            ShimmerBottomAboutBarItemAd(
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

            }


        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),
            ) {

                if (navController != null) {
                    DefaultCardSample(
                        textInCardBox = "sgpa".uppercase(),
                        navController = null,
                        route = null
                    )
                }
                if (navController != null) {
                    DefaultCardSample(
                        textInCardBox = "cgpa".uppercase(),
                        navController = null, route = null
                    )
                }

            }


        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Four_Grading_System_Mode_Preview(

) {
    Four_Grading_System_Mode_Screen(
        navController = null, null, null, null
    )
}
