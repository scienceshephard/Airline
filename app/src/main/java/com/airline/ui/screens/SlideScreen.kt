package com.airline.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airline.R
import com.airline.ui.theme.CyanBlue
import com.airline.ui.theme.LightBlue
import kotlinx.coroutines.launch
import com.airline.ui.theme.Typography as Typography1

@Composable
fun SlideScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        OnboardPage(
            navController = navController
        )
    }
}

@Composable
fun MyButton(
    click:() -> Unit,
    btnText: String ="Continue",
    contentColor:Color = Color.White,
    containerColor: Color = CyanBlue ){
    Button(
        onClick = click,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = btnText, style = Typography1.displayMedium)
    }
}

@Composable
fun OnboardPage(
    navController: NavHostController
){
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 3 })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    1 -> {
                        OnboardingPage(
                            displayHead = "Compare and save",
                            content = "See deals from hundred of travel sites at once then choose where to book",
                            urlImage = R.drawable.slide_screen_first_image
                        )
                    }

                    0 -> {
                        OnboardingPage(
                            displayHead = "Catch flights, Not feelings",
                            content = "Discover amazing airfares deals with the flights deals expert alert",
                            urlImage = R.drawable.slide_screen_second_image
                        )
                    }

                    2 -> {
                        OnboardingPage(
                            displayHead = "Tropical vacation",
                            content = "Book your island getaway, where the tropis meets your dreams.",
                            urlImage = R.drawable.slide_screen_third_image
                        )
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
            .weight(0.1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DotsIndicator(
                totalDots = 3,
                selectedIndex = pagerState.currentPage,
                pagerState = pagerState,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            if(pagerState.currentPage == 2) {
                MyButton(
                    btnText = "Get Started",
                    click = {
                        navController.navigate(Screen.AuthGraph.route){
                            popUpTo(Screen.SlideScreen.route) { inclusive = true }
                        }
                    }
                )
            } else{
                MyButton(
                    btnText = "Continue",
                    click = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                )
            }
            if(pagerState.currentPage != 2) {
                MyButton(
                    btnText = "Skip",
                    contentColor = CyanBlue,
                    containerColor = LightBlue,
                    click = {
                        scope.launch {
                            pagerState.animateScrollToPage(2)
                        }
                    }
                )
            }

        }
    }
}
@Composable
fun OnboardingPage(urlImage: Int, displayHead: String, content: String){
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = urlImage),
            contentDescription = "images slide",
        modifier =
            Modifier.weight(3f).fillMaxWidth()
        )
        Column {
            ResponsiveHeading(displayHead)
            Text(
                text = content,
                style = Typography1.displayMedium
            )
        }
    }
}
@Composable
fun ResponsiveHeading(text: String){
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        val fontSize = when {
            maxWidth < 360.dp -> 26.sp   // very small phones
            else -> 28.sp
        }
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = Typography1.titleLarge.fontWeight,
            textAlign = Typography1.titleLarge.textAlign
        )
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    selectedColor: Color = Color.Blue,
    unSelectedColor: Color = Color.Gray
){
    Row(
        modifier= modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        repeat(totalDots){ index ->
            val pageOffset = (pagerState.currentPage + pagerState.currentPageOffsetFraction) -index
            val  size by animateDpAsState(
                targetValue = if(index == pagerState.currentPage) 12.dp else 8.dp,
                label = "dotSize"
            )
            val color by animateColorAsState(
                targetValue = if(index == pagerState.currentPage) selectedColor else unSelectedColor,
                label = "dotColor"
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}