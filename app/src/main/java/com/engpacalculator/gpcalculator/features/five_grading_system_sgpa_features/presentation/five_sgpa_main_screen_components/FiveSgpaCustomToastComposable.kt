package com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.five_sgpa_main_screen_components

import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomToast(
    message: String,
    durationMillis: Long = 2000
) {
    var visible by remember {
        mutableStateOf(true)
    }

    //Start a coroutine to hide the specified value after a certain duration
    LaunchedEffect(key1 = true) {
        val handler = android.os.Handler(Looper.getMainLooper())
        handler.postDelayed({
            visible = false
        }, durationMillis)
    }

    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                shape = RoundedCornerShape(4.dp),
                elevation = 8.dp,
            ) {
                Text(
                    text = message,
                    modifier = Modifier.padding(16.dp)
                )

            }

        }
    }
}
