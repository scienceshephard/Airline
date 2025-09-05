package com.airline.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.airline.ui.theme.AnimatedSplashScreen
import com.airline.ui.screens.Screen
import com.airline.ui.screens.SlideScreen

@Composable
fun SetupNavGraph( navController: NavHostController ){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            composable(route = Screen.Splash.route) {
                AnimatedSplashScreen(navController = navController)
            }

            navigation(
                startDestination = Screen.SlideScreen.route,
                route = Screen.HomeGraph.route
            ) {
                composable(route = Screen.SlideScreen.route) {
                    SlideScreen(
                    navController = navController
                    )
                }
                composable(route = Screen.SlideScreen.Slide1.route) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Red)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}