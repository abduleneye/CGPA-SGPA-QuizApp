package com.example.gpcalculator.presentation.course_list_screen_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gpcalculator.R


@Composable
fun ResultBottomSheetContent(

    state: DialogBoxState
) {

    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
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


            Text(text = "Your Gp is:", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "${state.finalResult}", fontSize = 30.sp, fontWeight = FontWeight.Bold)


        }
    }

}

