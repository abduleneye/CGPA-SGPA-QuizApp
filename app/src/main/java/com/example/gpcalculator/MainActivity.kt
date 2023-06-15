package com.example.gpcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gpcalculator.ScreenElements.*
import com.example.gpcalculator.myViewModels.FirstViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var viewModel = viewModel<FirstViewModel>()
            val state by viewModel.dbState.collectAsState()
            val statetwo by viewModel.courseEntries.collectAsState()




            MainScreen(onEvent = viewModel::onEvent, state = state, stateTwo = statetwo)



            }
        }
    }



