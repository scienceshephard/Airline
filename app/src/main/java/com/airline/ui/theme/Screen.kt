package com.airline.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airline.R

@Composable
fun Container(frame: @Composable () -> Unit){
    Column (
        modifier = Modifier
            .fillMaxSize()

    ) {
        frame()
    }
}

@Composable
fun SplashScreenPage(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_image),
            contentDescription = "Splash Screen",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
fun PreviewContainer(){
    Container {
        SplashScreenPage()
    }
}