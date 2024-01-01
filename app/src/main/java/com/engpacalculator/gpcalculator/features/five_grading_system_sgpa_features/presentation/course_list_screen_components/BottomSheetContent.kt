package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.course_list_screen_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetState
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engpacalculator.gpcalculator.R
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultBottomSheetContent(

    state: DialogBoxState,
    sheetState: BottomSheetState,
    onEvent: (DialogBoxUiEvents) -> Unit
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(AppBars)
            .clickable {
                scope.launch {
                    if (sheetState.isCollapsed) {
                        sheetState.expand()
                    } else {
                        sheetState.collapse()
                    }
                }
            },
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Icon(
                painter = painterResource(id = R.drawable.horizontal_rule),
                contentDescription = "handle",
                modifier = Modifier
                    .padding(top = 0.dp),
                tint = Color.DarkGray
            )


            Row {
                Text(text = "Your SGPA is:", fontSize = 20.sp)

                Button(onClick = {
                    onEvent(DialogBoxUiEvents.showSaveResultDBox)

                }) {

                    Text(text = "S")

                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "${state.finalResult}", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "${state.gpaDescriptor}", fontSize = 20.sp, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "${state.remark}", fontSize = 20.sp, fontWeight = FontWeight.Light)


        }
    }

}

