package com.engpacalculator.gpcalculator.core.ads_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
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
fun ShimmerBottomAboutBarItemAd(
    isLoading: FiveSgpaUiStates,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    adId: String,
    onEvent: (FiveGpaUiEvents) -> Unit
) {

    AnchoredAdaptiveBannerAboutScreen(
        modifier = modifier,
        adId = adId,
        isLoading = FiveSgpaUiStates(),
        onEvent = onEvent
    )

    if (isLoading.aboutAdShimmerEffectVisibility) {
        Row(modifier = modifier) {

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .shimmerEffect()
            ) {

            }

        }
    } else {
        contentAfterLoading()

    }

}


@Composable
fun ShimmerBottomHomeBarItemAd(
    isLoading: FiveSgpaUiStates,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    adId: String,
    onEvent: (FiveGpaUiEvents) -> Unit
) {

    AnchoredAdaptiveBannerHomeScreen(
        modifier = modifier,
        adId = adId,
        isLoading = FiveSgpaUiStates(),
        onEvent = onEvent
    )

    if (isLoading.homeAdShimmerEffectVisibility) {
        Row(modifier = modifier) {

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .shimmerEffect()
            ) {

            }

        }
    } else {
        contentAfterLoading()

    }

}

@Composable
fun TestText() {
    Text(text = "Wala")
}


@Composable
fun AnchoredAdaptiveBannerHomeScreen(
    modifier: Modifier,
    adId: String,
    isLoading: FiveSgpaUiStates,
    onEvent: (FiveGpaUiEvents) -> Unit

) {
    val currentScreenWidth = LocalConfiguration.current.screenWidthDp
    val context = LocalContext.current

    val name = AndroidView(
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
                onEvent(FiveGpaUiEvents.showHomeAdShimmerEffect)
                super.onAdFailedToLoad(p0)

            }

            override fun onAdLoaded() {
//                Toast.makeText(context, "Ad Loaded", Toast.LENGTH_SHORT).show()
//                adLoaded = true
                onEvent(FiveGpaUiEvents.hideHomeAdShimmerEffect)
                super.onAdLoaded()

            }
        }

    }

}

@Composable
fun AnchoredAdaptiveBannerAboutScreen(
    modifier: Modifier,
    adId: String,
    isLoading: FiveSgpaUiStates,
    onEvent: (FiveGpaUiEvents) -> Unit

) {
    val currentScreenWidth = LocalConfiguration.current.screenWidthDp
    val context = LocalContext.current

    val name = AndroidView(
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
                onEvent(FiveGpaUiEvents.showAboutAdShimmerEffect)
                super.onAdFailedToLoad(p0)

            }

            override fun onAdLoaded() {
//                Toast.makeText(context, "Ad Loaded", Toast.LENGTH_SHORT).show()
//                adLoaded = true
                onEvent(FiveGpaUiEvents.hideAboutAdShimmerEffect)
                super.onAdLoaded()

            }
        }

    }

}

//
//@Composable
//fun AnchoredAdaptiveBannerAboutScreen(
//    modifier: Modifier,
//    adId: String,
//    isLoading: FiveSgpaUiStates,
//    onEvent: (FiveGpaUiEvents) -> Unit
//
//) {
//    val currentScreenWidth = LocalConfiguration.current.screenWidthDp
//    val context = LocalContext.current
//
//    AndroidView(
//        modifier = modifier,
//        factory = { context ->
//            AdView(context).apply {
//
//                val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                    context,
//                    currentScreenWidth
//                )
//
//                setAdSize(adSize)
//
//                adUnitId = adId
//
//                loadAd(AdRequest.Builder().build())
//
//
//            }
//        }
//    ) {
//
//
//        it.adListener = object : AdListener() {
//            override fun onAdFailedToLoad(p0: LoadAdError) {
////                Toast.makeText(context, "Ad load failed", Toast.LENGTH_SHORT).show()
//                super.onAdFailedToLoad(p0)
//
//            }
//
//            override fun onAdLoaded() {
////                Toast.makeText(context, "Ad Loaded", Toast.LENGTH_SHORT).show()
////                adLoaded = true
//                onEvent(FiveGpaUiEvents.hideAboutAdShimmerEffect)
//                super.onAdLoaded()
//
//            }
//        }
//
//    }
//}
//
