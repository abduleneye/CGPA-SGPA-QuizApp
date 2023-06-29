package com.example.gpcalculator.ScreenElements

import GpCalculatorPrototype.Data.GpData
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gpcalculator.ui.theme.Cream


@Composable
fun TotalCoursesListCardViewToDisplay(
    data: ArrayList<GpData>
){

    LazyColumn(){
        itemsIndexed(items = data){index, item ->

            MyCardView(info = item, index)

        }



    }


}


@Composable
fun MyCardView(
    info: GpData,
    index: Int
){

    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clickable {
                Toast.makeText(context,"Clicked on Item $index", Toast.LENGTH_SHORT).show()
            },
        backgroundColor = Cream


    ) {
        Box(modifier = Modifier
            .fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = info.courseCode, fontWeight = FontWeight.Bold)
                Row(
                    modifier = Modifier
                    .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {

                    Text(text = info.courseGrade, fontWeight = FontWeight.Bold)
                    Text(text = info.courseUnit.toString(), fontWeight = FontWeight.Bold)

                }
                
            }
            
        }
    }
}