package com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.five_cgpa_main_screen_components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eneye.enquizgpa.core.navigation.Screen
import com.eneye.enquizgpa.features.five_grading_system_cgpa_features.presentation.FiveCgpaUiStates
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaViewModel
import com.eneye.enquizgpa.ui.theme.AppBars
import com.eneye.enquizgpa.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FiveCgpaTopAppBarAndOptionsMenu(
    onEvent: (FiveGpaUiEvents) -> Unit,
    calcViewModel: FiveGpaViewModel,
    dbState: FiveCgpaUiStates,
    navController: NavController,
    sheetState: BottomSheetState,
    mFirebaseAnalytics: FirebaseAnalytics,

    ) {
    var optionsMenuState by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    val context = LocalContext.current




    CenterAlignedTopAppBar(

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = AppBars,

            ),

        title = {

            Text(
                text = "5.0 Cgpa Calculator",
                fontSize = 20.sp,

                )
        },
        actions = {
            IconButton(onClick = {

                optionsMenuState = !optionsMenuState

            }) {
                Icon(Icons.Default.MoreVert, contentDescription = "more")

            }

            DropdownMenu(
                expanded = optionsMenuState, onDismissRequest = {
                    optionsMenuState = false
                },
                modifier = Modifier
                    .background(Cream),
                offset = DpOffset(0.0.dp, 2.0.dp)
            ) {

                //Records

                DropdownMenuItem(onClick = {
                    navController.navigate(Screen.Five_Cgpa_Records_Screen.route)
                    onEvent(FiveGpaUiEvents.loadFiveCgpaResult)
                    optionsMenuState = !optionsMenuState

                }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Records",
                            fontSize = 16.sp,
                            style = TextStyle(baselineShift = BaselineShift(0.199f))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(Icons.Default.List, contentDescription = "About")
                    }


                }


//About Item

                DropdownMenuItem(onClick = {
                    val params = Bundle()
                    params.putString(
                        "FiveCgpaAboutButton",
                        "FiveCgpaAboutButtonClicked"
                    )
                    mFirebaseAnalytics.logEvent("FiveCgpaAboutButton", params)
                    onEvent(FiveGpaUiEvents.showFiveCgpaIntroDialogBox)
                    optionsMenuState = !optionsMenuState

                }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "About",
                            fontSize = 16.sp,
                            style = TextStyle(baselineShift = BaselineShift(0.199f))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(Icons.Default.Info, contentDescription = "About")
                    }


                }


            }

        },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = {
                //navController.navigate(Screen.Home.route)
                navController.popBackStack()
                //navController.popBackStack()
            }) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow"
                )

            }
        },

        )


}