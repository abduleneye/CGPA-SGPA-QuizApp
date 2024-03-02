package com.engpacalculator.gpcalculator.about_screen_components.ui.theme

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.engpacalculator.gpcalculator.R
import com.engpacalculator.gpcalculator.core.ads_components.FiveScreensBottomBannerAd
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.engpacalculator.gpcalculator.ui.theme.AppBars
import com.engpacalculator.gpcalculator.ui.theme.Cream
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavController,
    adId: String,
    state: FiveSgpaUiStates,
    onEvent: (FiveGpaUiEvents) -> Unit,
    mFirebaseAnalytics: FirebaseAnalytics


) {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val naver = navController.currentDestination?.route
    val pos = navController.currentBackStackEntry
//    (Toast.makeText(
//        navController.context,
//        "cur dest:  $naver: cur  bste: $pos ",
//        Toast.LENGTH_SHORT
//    ).show())

    var scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "About")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBars
                ),

                navigationIcon = {
                    IconButton(onClick = {
                        //navController.navigate(Screen.Home.route)
                        navController.popBackStack()
                        //navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Arrow"
                        )

                    }
                },

                )
        },
        bottomBar = {

            BottomAppBar(
                containerColor = Cream,
                contentPadding = PaddingValues(0.dp)

            ) {


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
    ) {


        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                //.verticalScroll(scrollState)
                .fillMaxSize()
                .padding(it)
                .background(color = Cream)

        ) {
            item {
                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(500.dp)
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 10.dp, end = 10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Cream
                    )


                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            text = "About App",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold


                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize(),
                            text = "EnGPA(Grade point average)Calculator is an app developed as a hobby project for university and polytechnic students using the(5.0) and (4.0) grading system respectively.\n" +
                                    "Manage your course entries, calculate your (SGPA),(CGPA) and simulate your future performance with different scenarios.\n" +
                                    "Key features include:\n" +
                                    "Easy Course Management: Add, edit, and remove course details seamlessly.\n" +
                                    "Result Storage: Save and retrieve your results seamlessly.\n" +
                                    "Accurate (SGPA) and (CGPA) Calculation: Effortlessly calculate your (SGPA) and (CGPA) based on your  course grades and credit loads.\n" +
                                    "Simulation Analysis: Use our simulation capability by editing your course entry grades  and credit unit to  explore how your (SGPA) might change under different circumstances.\n" +
                                    "We've also added a quiz section as a bonus, more info in the quiz section.\n" +
                                    "Tip: Click  on the three dots on  a course entry to edit or remove course details and simulate your  result.\n",
                            textAlign = TextAlign.Start,
                            fontSize = 15.sp,


                            )


                    }


                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(460.dp)
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 16.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = Cream
                    )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Text(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            text = "About Developer",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold


                        )

                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            text = "Abdulhameed Abdulhakeem Eneye is a passionate tech enthusiast currently pursuing studies at the Federal University of Technology Minna,\n" +
                                    "Although relatively new to android development, he loves exploring the latest tools and technologies.\n" +
                                    "Abdulhakeem has keen interest in open source projects and is open to freelance opportunities.\n" +
                                    "Get in touch with Abdulhameed for: new app  features request, collaborations and projects through the platforms below:\n",
                            textAlign = TextAlign.Start,
                            fontSize = 15.sp,


                            )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            val context = LocalContext.current
                            Image(
                                painter = painterResource(id = R.drawable.gmail_sixty_four),
                                contentDescription = "handle",
                                modifier = Modifier
                                    .padding(top = 0.dp)
                                    .clickable {
                                        val params = Bundle()
                                        params.putString(
                                            "GmailButton",
                                            "GmailButtonClicked"
                                        )
                                        mFirebaseAnalytics.logEvent("gmailButton", params)
                                        val recipientEmail = "abdulhakeemab15@gmail.com"
                                        val subject = "Contacting Developer"
                                        val message = "Write something"

                                        val uriText = "mailto:$recipientEmail" +
                                                "?subject=" + Uri.encode(subject) +
                                                "&body=" + Uri.encode(message)
                                        val uri = Uri.parse(uriText)
                                        val emailIntent = Intent(Intent.ACTION_SENDTO, uri)

                                        startActivity(
                                            context,
                                            Intent.createChooser(emailIntent, "Send email"),
                                            null
                                        )
                                    }


                            )

                            //Spacer(modifier = Modifier.width(8.dp))

                            Image(
                                painter = painterResource(id = R.drawable.wm),
                                contentDescription = "handle",
                                modifier = Modifier
                                    .padding(top = 0.dp)
                                    .clickable {
                                        val params = Bundle()
                                        params.putString(
                                            "WhatsappButton",
                                            "WhatsappButtonClicked"
                                        )
                                        mFirebaseAnalytics.logEvent("whatsappButton", params)

                                        val phoneNumber = "+2347018341516"
                                        val smsPhoneNumber = "+2347035106986"
                                        val message = "Hello, Contacting Developer"
                                        val uri = Uri.parse("smsto:$phoneNumber")
                                        val intent = Intent(Intent.ACTION_SENDTO, uri)
                                        intent.setPackage("com.whatsapp")
                                        intent.putExtra("sms_body", message)

                                        try {
                                            startActivity(context, intent, null)
                                        } catch (e: Exception) {
                                            //Normal whatsApp not installed, fallback to SMS
                                            try {
                                                intent.setPackage("com.whatsapp.w4b")
                                                intent.putExtra("sms_body", message)
                                                startActivity(context, intent, null)
                                            } catch (e: Exception) {
                                                //whatsappBusiness not installed, fallback to SMS
                                                val smsIntent = Intent(
                                                    Intent.ACTION_SENDTO,
                                                    Uri.parse("smsto:$smsPhoneNumber")
                                                )
                                                smsIntent.putExtra("sms_body", message)
                                                startActivity(context, smsIntent, null)
                                            }


                                        }
                                    }


                            )

                            //Spacer(modifier = Modifier.width(8.dp))

                            Image(
                                painter = painterResource(id = R.drawable.lm),
                                contentDescription = "handle",
                                modifier = Modifier
                                    .padding(top = 0.dp)
                                    .clickable {
                                        val params = Bundle()
                                        params.putString(
                                            "LinkdnButton",
                                            "LinkdnButtonClicked"
                                        )
                                        mFirebaseAnalytics.logEvent("linkdnButton", params)
                                        val linkdInProfileUrl =
                                            "https://www.linkedin.com/in/abdulhakeem-eneye-127b93274?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
                                        val intent =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(linkdInProfileUrl))
                                        intent.setPackage("com.linkedin.android")

                                        try {
                                            startActivity(context, intent, null)

                                        } catch (e: Exception) {
                                            // LinkedIn app isn't installed, open LinkedIn in a browser
                                            startActivity(
                                                context,
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    Uri.parse(linkdInProfileUrl)
                                                ),
                                                null
                                            )
                                        }
                                    }


                            )


                        }

                    }
                }


            }


        }


    }

}