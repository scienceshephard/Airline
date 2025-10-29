package com.airline.ui.screens.main_screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.airline.R

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()
    ) {
        Card {
            Image(
                painter = painterResource(id = R.drawable.airplane),
                contentDescription = "airplane"
            )
        }
    }
}
