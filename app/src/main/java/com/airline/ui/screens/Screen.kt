package com.airline.ui.screens

sealed class Screen(val route:String){
    object Splash: Screen("splash_screen")
    object MainGraph: Screen("main_graph")
    object SlideScreen: Screen("slide_screen")

    object AuthGraph: Screen("auth_graph")
    object Login : Screen("auth_graph/login")
    object Signup : Screen("auth_graph/signup")

}