package com.engpacalculator.gpcalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.ui.theme.Cream

@Composable
fun DefaultCardSample(
    textInCardBox: String,
    navController: NavController,
    route: String
) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),

        modifier = Modifier
            .height(164.dp)
            .width(164.dp)
            .clickable {
                navController.navigate(route)
            },

        ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Cream)
        ) {

            Text(text = textInCardBox)
        }


    }

}