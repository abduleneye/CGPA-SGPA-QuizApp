package com.engpacalculator.gpcalculator.HomeScreen

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.DefaultCardSample
import com.engpacalculator.gpcalculator.R
import com.engpacalculator.gpcalculator.core.ads_components.FiveScreensBottomBannerAd
import com.engpacalculator.gpcalculator.core.navigation.Screen
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController?,
    adId: String?,
    state: FiveSgpaUiStates?,
    onEvent: ((FiveGpaUiEvents) -> Unit)?

) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val clipboardManager = LocalClipboardManager

    var res by remember {
        mutableStateOf("")
    }



    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cream),

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Home ")
                },

                actions = {
                    IconButton(
                        onClick = {

                            scope.launch {
                                val localToken = Firebase.messaging.token.await()
                                res = localToken
                                // clipboardManager.current.setText(AnnotatedString(localToken))
                                Toast.makeText(context, localToken, Toast.LENGTH_LONG).show()
                                Log.d("Token", localToken)
                            }


//                            var notice = MyNotification(
//                                context = context,
//                                title = "FCM: AppUpdate",
//                                msg = "AppUpdate Available"
//                            )
//                            notice.FireNotification()
                            val packageName = "com.engpacalculator.gpcalculator"
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=$packageName")
                            )

                            try {
                                context.startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                val intentWeb = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                                )
                                context.startActivity(intentWeb)
                            }

                        },


                        ) {
                        Column {
                            Icon(
                                painter = painterResource(id = R.drawable.app_update_icon),
                                contentDescription = "Update App"
                            )
                            Text(text = "Update App", fontSize = 6.sp)

                        }


                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),


                )


        },


        bottomBar = {

            BottomAppBar(
                containerColor = Cream,
                contentPadding = PaddingValues(0.dp)

            ) {


                if (state != null) {
                    if (onEvent != null) {
                        if (adId != null) {
                            FiveScreensBottomBannerAd(
                                isLoading = state,
                                onEvent = onEvent,
                                contentAfterLoading = {

                                },
                                modifier = Modifier,
                                adId = adId
                            )
                        }
                    }
                }

            }


        }
    ) {

        var scrollState = rememberScrollState()


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // .verticalScroll(state = scrollState)
                .background(color = Cream)
                .padding(it),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = it.calculateTopPadding())
                    //  .padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),

                ) {

                    if (navController != null) {
                        DefaultCardSample(
                            textInCardBox = "5.0 grading system".uppercase(),
                            navController = navController,
                            Screen.Five_Screen.route,
                            modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(start = 16.dp)


                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))


                    if (navController != null) {
                        DefaultCardSample(
                            textInCardBox = "4.0 grading system".uppercase(),
                            navController = navController,
                            Screen.Four_Screen.route,
                            modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(end = 16.dp)
                        )
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = it.calculateBottomPadding())

                    // .weight(0.5f)

                    //.padding(start = it.calculateStartPadding(layoutDirection = LayoutDirection.Ltr)),

                ) {

                    if (navController != null) {
                        DefaultCardSample(
                            textInCardBox = "quiz".uppercase(),
                            navController = navController,
                            Screen.Quiz_Mode_Screen.route,
                            modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(start = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))


                    if (navController != null) {
                        DefaultCardSample(
                            textInCardBox = "About".uppercase(),
                            navController,
                            Screen.About.route,
                            modifier = Modifier
                                .height(164.dp)
                                .weight(0.5f)
                                .width(164.dp)
                                .padding(end = 16.dp)

                        )
                    }


                }


            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeScreenPreview(

) {
    HomeScreen(navController = null, null, state = null, onEvent = null)
}