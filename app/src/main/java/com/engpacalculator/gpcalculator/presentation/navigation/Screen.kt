package com.engpacalculator.gpcalculator.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object About : Screen("about_screen")
    object Home : Screen("home_screen")
}