package com.eneye.enquizgpa.core

import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eneye.enquizgpa.R
import com.eneye.enquizgpa.core.ads_components.shimmerEffect
import com.eneye.enquizgpa.core.navigation.Screen
import com.eneye.enquizgpa.ui.theme.Cream
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplash(
    navController: NavHostController,
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
    alpha: Float,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .shimmerEffect()
            .background(color = Cream),
        contentAlignment = Alignment.Center
    ) {

        Card(
            ///
            //elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = Cream
            )
            //.background(color = Cream)

        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Image(
                    painter = painterResource(id = R.drawable.ssf),
                    contentDescription = "SplashScreen",
                    contentScale = ContentScale.FillBounds,

                    )
            }


        }


    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PrevSplash() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cream),
        contentAlignment = Alignment.Center
    ) {

        Card(
            ///
            //elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = Cream
            )
            //.background(color = Cream)

        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Image(
                    painter = painterResource(id = R.drawable.ssf),
                    contentDescription = "SplashScreen",
                    contentScale = ContentScale.FillBounds,

                    )
            }


        }


    }

}