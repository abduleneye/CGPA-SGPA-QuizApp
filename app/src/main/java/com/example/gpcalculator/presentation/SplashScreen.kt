package com.example.gpcalculator.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.gpcalculator.R
import com.example.gpcalculator.presentation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplash(
    navController: NavHostController
) {


    var startAnimation by remember {
        mutableStateOf(false)
    }
    //val aphaAnim

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation == true) 1f else 0f,
        animationSpec = tween(
            3000
        )
    )
    Splash(alpha = alphaAnim.value)

}

@Composable
fun Splash(
    alpha: Float
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.newest),
            contentDescription = "SplashScreen",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .alpha(alpha = alpha)


        )

    }
}