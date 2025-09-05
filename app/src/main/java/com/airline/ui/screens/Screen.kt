package com.airline.ui.screens

sealed class Screen(val route:String){
    object Splash: Screen("splash_screen")
    object HomeGraph: Screen("home_graph")
    object SlideScreen: Screen("slide_screen"){
        object Slide1: Screen("slide_screen/slide1")
    }
    object AuthGraph: Screen("auth_graph")
    object Auth: Screen("auth_screen"){
        object SignupScreen: Screen("auth_screen/sign_up")
        object LoginScreen: Screen("auth_screen/login")
    }

}