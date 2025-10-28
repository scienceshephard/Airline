package com.airline.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.airline.ui.screens.LoginScreen
import com.airline.ui.screens.MainScreens
import com.airline.ui.theme.AnimatedSplashScreen
import com.airline.ui.screens.Screen
import com.airline.ui.screens.SignupScreen
import com.airline.ui.screens.SlideScreen
import com.airline.ui.screens.main_screens.BookingsScreen
import com.airline.ui.screens.main_screens.HomeScreen
import com.airline.ui.screens.main_screens.NotificationScreen
import com.airline.ui.screens.main_screens.ProfileScreen

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
                route = Screen.MainGraph.route
            ) {
                composable(route = Screen.SlideScreen.route) {
                    SlideScreen(navController = navController)
                }
            }
            navigation(
                startDestination = Screen.Login.route,
                route = Screen.AuthGraph.route
            ){
                composable(Screen.Login.route) {
                    LoginScreen(navController)
                }
                composable (Screen.Signup.route){
                    SignupScreen(navController)
                }
            }
            navigation(
                startDestination = MainScreens.Home.route,
                route = Screen.MainGraph.route
            ){
                composable(
                    route = MainScreens.Home.route
                ) {
                    HomeScreen()
                }
                composable(
                    route = MainScreens.Notification.route
                ){
                    NotificationScreen()
                }
                composable (
                    route = MainScreens.Profile.route
                ){
                    ProfileScreen()
                }
                composable (
                    route = MainScreens.Bookings.route
                ){
                    BookingsScreen()
                }
            }
        }
    }
}