package com.example.gpcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gpcalculator.ScreenElements.DialogBoxUiEvents
import com.example.gpcalculator.ScreenElements.ShowDialogBox
import com.example.gpcalculator.myViewModels.FirstViewModel
import com.example.gpcalculator.ui.theme.GpCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var viewModel = viewModel<FirstViewModel>()
            val state by viewModel.dbState.collectAsState()

           Box(
               contentAlignment = Alignment.Center,
               modifier = Modifier
                   .fillMaxSize()
           ) {

               Column(
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center
               ) {

                   ShowDialogBox(state = state, onEvent = viewModel::onEvent)
                   Button(onClick = { viewModel.onEvent(DialogBoxUiEvents.showDBox) }) {

                       Text(text = "Press Me")

                   }

               }

           }


            }
        }
    }



