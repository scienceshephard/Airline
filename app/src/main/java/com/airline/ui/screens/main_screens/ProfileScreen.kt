package com.airline.ui.screens.main_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airline.R
import com.airline.ui.theme.Typography


@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .border(1.dp, Color.Red)

        ) {
            Image(
                painter = painterResource(R.drawable.pro),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize(0.4f)
                        .border(
                            width = 1.dp,
                            shape = CircleShape,
                            color = Color.Gray
                        )
                        .clip(CircleShape),
                )
                Text(
                    text = "Raphael",
                )
                Text(
                    text = "Silver Member",
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Wallet Amount"
                    )
                    Text(
                        text = "$500.00"
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Payment Information",
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        textAlign = TextAlign.Left
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .background(Color.White),
                    ) {
                        ExpandedButton(
                            title = "Rewards and Wallet",
                            onClick = {

                            },
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            isSelected = false
                        )
                        HorizontalDivider(
                            color = Color(0xFFE0E0E0),
                            thickness = 1.dp
                        )
                        ExpandedButton(
                            title = "Payment method",
                            onClick = {

                            },
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            isSelected = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandedButton(
    title: String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation( defaultElevation = 0.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = title,
                color = Color.Black
            )
            Icon(
                imageVector = if(isSelected) Icons.Filled.KeyboardArrowDown
                                else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Navigate",
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Prev(){
    ProfileScreen()
}