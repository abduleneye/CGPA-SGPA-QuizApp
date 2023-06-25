package com.example.gpcalculator.ScreenElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gpcalculator.R


@Composable
fun  ResultBottomSheetContent(

    state: DialogBoxState
){
    
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
                painter = painterResource(id = R.drawable.bottom_sheet_handle),
                contentDescription = "handle",
                modifier = Modifier
                    .padding(top = 0.dp)
                    ,
                tint = Color.Gray
            )


            Text(text = "Your Gp is:", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(5.dp))
            
            Text(text = "${state.finalResult}", fontSize = 30.sp, fontWeight = FontWeight.Bold)




            
        }
    }
    
}

