package com.example.gpcalculator.presentation.myViewModels.course_list_screen_component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gpcalculator.presentation.course_list_screen_components.DialogBoxState
import com.example.gpcalculator.presentation.course_list_screen_components.ResultBottomSheetContent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultBottomSheet(
    state: DialogBoxState
) {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(

        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()
    val sheetWidth = remember {
        mutableStateOf(60.dp)
    }

    BottomSheetScaffold(
        sheetContent = { ResultBottomSheetContent(state) },
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = Color.LightGray,
        modifier = Modifier
            //.fillMaxWidth()
            .width(sheetWidth.value),
        sheetPeekHeight = 0.dp,
        drawerGesturesEnabled = true,
        sheetElevation = 100.dp
    ) {

    }


}