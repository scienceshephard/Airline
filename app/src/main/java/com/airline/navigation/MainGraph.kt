package com.airline.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
            .background(color = Color.Black, shape = RoundedCornerShape(20.dp)),
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
                    .clickable{
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
                        .border(
                            width = if(isSelected) 5.dp else 0.dp,
                            color = if(isSelected) Color.White else Color.Transparent,
                            shape = CircleShape
                        )
                    .background(
                        color = if(isSelected) Color.Blue else Color.Transparent,
                        shape = CircleShape
                    ),
                    contentAlignment = Alignment.Center,

                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.image_desc,
                        tint = Color.White,
                        modifier = Modifier
                            .size(25.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun MainGraph(
    rootNavController: NavHostController
){
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            
            startDestination = MainScreens.Notification.route,
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
                BookingsScreen(navController)
            }
        }
    }
}

@Preview( showSystemUi = true, showBackground = true)
@Composable
fun MainPrev(){
    MainGraph(rootNavController = rememberNavController())
}