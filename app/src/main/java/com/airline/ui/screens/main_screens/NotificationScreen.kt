package com.airline.ui.screens.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airline.ui.theme.CyanBlue
import com.airline.ui.theme.Typography


@Composable
fun NotificationScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                text = "BACK",
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .clickable{

                    }
            )
            Text(
                text = "Flight",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "No notification",
                style = Typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.Gray
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = CyanBlue
                ),
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = "Create Alert",
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationPreview(){
    NotificationScreen()
}