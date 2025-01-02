package com.eneye.enquizgpa.core.ads_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveGpaUiEvents
import com.eneye.enquizgpa.features.five_grading_system_sgpa_features.presentation.FiveSgpaUiStates
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError


var adLoaded = false


@Composable
fun FiveScreensBottomBannerAd(
    isLoading: FiveSgpaUiStates,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    adId: String,
    onEvent: (FiveGpaUiEvents) -> Unit,
) {

    FiveAnchoredAdaptiveBannerAboutScreen(
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
fun FiveShimmerBottomHomeBarItemAd(
    isLoading: FiveSgpaUiStates,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    adId: String,
    onEvent: (FiveGpaUiEvents) -> Unit,
) {

    FiveAnchoredAdaptiveBannerHomeScreen(
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
fun FiveAnchoredAdaptiveBannerHomeScreen(
    modifier: Modifier,
    adId: String,
    isLoading: FiveSgpaUiStates,
    onEvent: (FiveGpaUiEvents) -> Unit,

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
fun FiveAnchoredAdaptiveBannerAboutScreen(
    modifier: Modifier,
    adId: String,
    isLoading: FiveSgpaUiStates,
    onEvent: (FiveGpaUiEvents) -> Unit,

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

