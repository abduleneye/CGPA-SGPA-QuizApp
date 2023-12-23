package com.engpacalculator.gpcalculator.features.uni_sgpa_features.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object About : Screen("about_screen")
    object Home : Screen("home_screen")
}