package com.example.gpcalculator.ScreenElements

import GpCalculatorPrototype.Data.CourseDataEntries
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownMenu(
    labelTextOne: String,
    labelTextTwo: String,
    dBState: DialogBoxState,
    onEvent: (DialogBoxUiEvents) -> Unit,


){

    var dataEntryObj = CourseDataEntries()

    var textFilledSize  by remember {
        mutableStateOf(Size.Zero)
    }

    var iconOne = if(dBState.isUnitDropDownMenuExpanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    var iconTwo = if(dBState.isUnitDropDownMenuExpanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Row {

           Column() {

               OutlinedTextField(
                   value = dBState.selectedCourseUnit,
                   onValueChange = {},
                   enabled = false,
                   modifier = Modifier
                       .onGloballyPositioned { co_ordinates ->

                           textFilledSize = co_ordinates.size.toSize()

                       }
                       .width(130.dp)
                       .height(70.dp),
                   label = {
                       Text(text = labelTextOne)
                   },
                   trailingIcon = {

                       Icon(
                           imageVector = iconOne,
                           contentDescription = "DropDownIcon",
                           modifier = Modifier
                               .clickable {

                                   if(dBState.isUnitDropDownMenuExpanded){
                                       onEvent(DialogBoxUiEvents.hideUnitMenuDropDown)

                                   }else{

                                       onEvent(DialogBoxUiEvents.showUnitMenuDropDown)


                                   }

                               }
                       )

                   }
               )
               
               DropdownMenu(
                   expanded = dBState.isUnitDropDownMenuExpanded,
                   onDismissRequest = { onEvent(DialogBoxUiEvents.showUnitMenuDropDown) },
                   modifier = Modifier
                       .width(with(LocalDensity.current){
                          textFilledSize.width.toDp()
                       })
                       .height(120.dp)
               ) {

                   dataEntryObj.unitList.forEach{courseUnit ->
                       
                       DropdownMenuItem(onClick = {
                           onEvent(DialogBoxUiEvents.setSelectedCourseUnit(courseUnit))
                           onEvent(DialogBoxUiEvents.hideUnitMenuDropDown)
                       }) {

                           Text(text = courseUnit)
                           
                       }



                   }



               }

           }

            Spacer(modifier = Modifier
                .width(20.dp))

            Column() {

                OutlinedTextField(
                    value = dBState.selectedCourseGrade,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier
                        .onGloballyPositioned { co_ordinates ->

                            textFilledSize = co_ordinates.size.toSize()

                        }
                        .width(130.dp)
                        .height(70.dp),
                    label = {
                        Text(text = labelTextTwo)
                    },
                    trailingIcon = {

                        Icon(
                            imageVector = iconTwo,
                            contentDescription = "DropDownIcon",
                            modifier = Modifier
                                .clickable {

                                    if (dBState.isGradeDropDownMenuExpanded){
                                        onEvent(DialogBoxUiEvents.hideGradeMenuDropDown)

                                    }else{
                                        onEvent(DialogBoxUiEvents.showGradeMenuDropDown)
                                    }

                                }
                        )

                    }
                )
                
                DropdownMenu(
                    expanded = dBState.isGradeDropDownMenuExpanded,
                    onDismissRequest = { onEvent(DialogBoxUiEvents.showGradeMenuDropDown) },
                    modifier = Modifier
                        .width(with(LocalDensity.current){
                            textFilledSize.width.toDp()
                        })
                        .height(120.dp)
                ) {

                    dataEntryObj.gradeList.forEach{courseGrade ->

                        DropdownMenuItem(onClick = {
                            onEvent(DialogBoxUiEvents.setSelectedCourseGrade(courseGrade))
                            onEvent(DialogBoxUiEvents.hideGradeMenuDropDown)
                        }) {

                            Text(text = courseGrade)

                        }

                    }
                    
                }

            }





        }

        
    }



}