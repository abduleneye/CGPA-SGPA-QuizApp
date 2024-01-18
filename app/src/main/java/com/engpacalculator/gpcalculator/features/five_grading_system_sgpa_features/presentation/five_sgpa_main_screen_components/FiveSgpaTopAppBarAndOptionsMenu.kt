package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomSheetState
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaViewModel
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FiveSgpaTopAppBarAndOptionsMenu(
    onEvent: (FiveSgpaUiEvents) -> Unit,
    calcViewModel: FiveSgpaViewModel,
    dbState: FiveSgpaUiStates,
    navController: NavController,
    sheetState: BottomSheetState

) {
    var optionsMenuState by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val size = calcViewModel.courseEntries.collectAsState().value.size




    CenterAlignedTopAppBar(

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = AppBars,

            ),

        title = {

            Text(
                text = "GpaCalculator",
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
//Clear Courses Option Menu Item
                DropdownMenuItem(
                    onClick = {
                        if (
                            dbState.enteredCourses.toInt() == 0
                        ) {

                            Toast.makeText(
                                context,
                                "No course(s) to clear yet",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            onEvent(FiveSgpaUiEvents.showClearConfirmationDBox)

                        }


                        optionsMenuState = !optionsMenuState
                        scope.launch {
                            if (sheetState.isExpanded) {
                                sheetState.collapse()
                            }
                        }

                    },
                    modifier = Modifier
                        .background(Cream)
                        .height(35.dp),


                    ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Clear courses",
                            fontSize = 16.sp,
                            style = TextStyle(baselineShift = BaselineShift(0.199f))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(Icons.Default.Clear, contentDescription = "clear courses")
                    }
                }
//Edit Base entry DialogBox Item
                DropdownMenuItem(onClick = {

                    if (
                        dbState.totalCourses.isBlank()
                    ) {

                        Toast.makeText(
                            context,
                            "Nothing to edit yet tst",
                            Toast.LENGTH_SHORT
                        ).show()

                        // onEvent(FiveSgpaUiEvents.showEditBaseEntryDBox)


                    } else {


                        onEvent(FiveSgpaUiEvents.showEditBaseEntryDBox)
                        scope.launch {
                            if (sheetState.isExpanded) {
                                sheetState.collapse()
                            }
                        }

                    }



                    optionsMenuState = !optionsMenuState
                    scope.launch {
                        if (sheetState.isExpanded) {
                            sheetState.collapse()
                        }
                    }
                }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Edit numbers",
                            fontSize = 16.sp,
                            style = TextStyle(baselineShift = BaselineShift(0.199f))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }


                }
//About Item

                DropdownMenuItem(onClick = {
                    navController.navigate(Screen.About.route)
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
//Records

                DropdownMenuItem(onClick = {
                    navController.navigate(Screen.Five_Sgpa_Records_Screen.route)
                    onEvent(FiveSgpaUiEvents.loadResult)
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


            }

        }

    )


}