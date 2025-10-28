package com.airline.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.airline.ui.screens.MainScreens
import com.airline.ui.screens.main_screens.BookingsScreen
import com.airline.ui.screens.main_screens.HomeScreen
import com.airline.ui.screens.main_screens.NotificationScreen
import com.airline.ui.screens.main_screens.ProfileScreen


@Composable
fun BottomNavBar(
    navController: NavHostController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val screens = listOf(
        MainScreens.Home,
        MainScreens.Notification,
        MainScreens.Bookings,
        MainScreens.Profile
    )
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Black)
            .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        screens.forEach { item ->
            val isSelected = currentDestination == item.route
            val offset by animateDpAsState(
                targetValue = if (isSelected) (-20.dp) else 0.dp,
                label = item.image_desc
            )
            Box(
                modifier = Modifier
                    .offset( y = offset)
                    .clickable {
                        navController.navigate(item.route){
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                modifier = Modifier
                        .size(56.dp)
                        .border(2.dp, Color.Blue, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.image_desc,
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun MainGraph(
    navController: NavHostController
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            startDestination = MainScreens.Home.route,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
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

@Preview( showSystemUi = true, showBackground = true)
@Composable
fun MainPrev(){
    MainGraph(navController = rememberNavController())
}