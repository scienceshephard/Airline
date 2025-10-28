package com.airline.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

sealed class MainScreens (val route: String, val icon: ImageVector, val image_desc: String){

    object Home: MainScreens(
        route = "main_graph/home",
        icon = Icons.Filled.Home,
        image_desc = "Home"
    )
    object Bookings: MainScreens(
        route = "main_graph/bokings",
        icon = Icons.Filled.Book,
        image_desc = "Bookings"
    )
    object Notification: MainScreens(
        route = "main_graph/notifications",
        icon = Icons.Filled.Notifications,
        image_desc = "Notifications"
    )
    object Profile: MainScreens(
        route = "main_graph/user",
        icon = Icons.Filled.AccountCircle,
        image_desc = "profile"
    )
}