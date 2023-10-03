package com.example.gpcalculator.presentation.ads_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError


//@Composable
//fun MainScreenBannerAd(
//    modifier: Modifier, adId: String
//) {
//    val newContext = LocalContext.current
//    Column(
//        modifier = modifier
//            .fillMaxSize()
//    ) {
//
//
//        AndroidView(
//            modifier = Modifier
//                .fillMaxWidth(),
//            factory = { context ->
//                AdView(context).apply {
//                    setAdSize(AdSize.BANNER)
//                    adUnitId = adId
//                    // Request an Ad
//                    loadAd(AdRequest.Builder().build())
//
//                    adListener = object : AdListener() {
//                        override fun onAdFailedToLoad(p0: LoadAdError) {
//                            Toast.makeText(newContext, "AdFailedToLoad", Toast.LENGTH_SHORT)
//                            super.onAdFailedToLoad(p0)
//                            Toast.makeText(newContext, "AdFailedToLoad", Toast.LENGTH_SHORT)
//
//                        }
//
//                        override fun onAdLoaded() {
//                            Toast.makeText(newContext, "Ad Loaded", Toast.LENGTH_SHORT)
//                            super.onAdLoaded()
//                            Toast.makeText(newContext, "Ad Loaded", Toast.LENGTH_SHORT)
//
//                        }
//                    }
//                }
//
//            }
//
//
//        )
//
//    }
//
//}
var adLoaded = false

@Composable
fun AnchoredAdaptiveBanner(modifier: Modifier, adId: String) {
    val currentScreenWidth = LocalConfiguration.current.screenWidthDp
    val context = LocalContext.current

    AndroidView(
        modifier = modifier,
        factory = { context ->
            AdView(context).apply {

                val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    context,
                    currentScreenWidth
                )

                setAdSize(adSize)

                adUnitId = adId

                loadAd(AdRequest.Builder().build())


            }
        }
    ) {


        it.adListener = object : AdListener() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
//                Toast.makeText(context, "Ad load failed", Toast.LENGTH_SHORT).show()
                super.onAdFailedToLoad(p0)

            }

            override fun onAdLoaded() {
//                Toast.makeText(context, "Ad Loaded", Toast.LENGTH_SHORT).show()
//                adLoaded = true
                super.onAdLoaded()

            }
        }

    }
}

