package com.airline.ui.screens.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airline.ui.theme.CyanBlue
import com.airline.ui.theme.TextColor
import com.airline.ui.theme.Typography


@Composable
fun BookingsScreen(navController: NavHostController){
    val activeButton = remember { mutableStateOf<Int?>(1) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text="BACK",
            modifier = Modifier.clickable {
                navController.popBackStack()
            },
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(
                onClick = {
                    activeButton.value = 1
                },
                colors= ButtonDefaults.buttonColors(
                    containerColor = if(activeButton.value == 1) CyanBlue else Color.Transparent,
                    contentColor =  if (activeButton.value == 1) Color.White else Color.Black
                ),
                shape = ButtonDefaults.elevatedShape,
            ) {
                Text(text = "ONE WAY")
            }
            Button(
                onClick = {
                    activeButton.value = 2
                },
                colors= ButtonDefaults.buttonColors(
                    containerColor = if(activeButton.value == 2) CyanBlue else Color.Transparent,
                    contentColor =  if (activeButton.value == 2) Color.White else Color.Black
                ),
                shape = ButtonDefaults.elevatedShape,
            ) {
                Text(text = "ROUND TRIP")
            }
            Button(
                onClick = {
                    activeButton.value = 3
                },
                colors= ButtonDefaults.buttonColors(
                    containerColor = if(activeButton.value == 3) CyanBlue else Color.Transparent,
                    contentColor =  if (activeButton.value == 3) Color.White else Color.Black
                ),
                shape = ButtonDefaults.elevatedShape,
            ) {
                Text(text = "MULTI-CITY")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (activeButton.value == 3) {
                    Text(
                        text = "Flight 1",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = Color.Transparent
                        )
                        .border(width = 1.dp, shape = RoundedCornerShape(20.dp), color = Color.Gray)
                        .padding(20.dp)
                ) {
                    Text("From")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = Color.Gray,
                            thickness = 1.dp
                        )
                        Row(
                            modifier = Modifier
                                .background(
                                    color = CyanBlue,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 15.dp)
                                .align(Alignment.TopEnd),
                        ) {
                            Icon(
                                imageVector = (Icons.Filled.ArrowUpward),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .height(30.dp)
                                    .graphicsLayer(scaleY = 2.5f, scaleX = 1.5f)
                            )
                            Icon(
                                imageVector = (Icons.Filled.ArrowDownward),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .height(30.dp)
                                    .graphicsLayer(scaleY = 2.5f, scaleX = 1.5f)
                            )
                        }
                    }
                    Text("To")
                }
            }
            if (activeButton.value == 3) {
                Column (
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Text(
                        text = "Flight 2",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Column(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(10.dp),
                                color = Color.Transparent
                            )
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(20.dp),
                                color = Color.Gray
                            )
                            .padding(20.dp)
                    ) {
                        Text("From")
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 4.dp),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                            Row(
                                modifier = Modifier
                                    .background(
                                        color = CyanBlue,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                    .padding(horizontal = 10.dp, vertical = 15.dp)
                                    .align(Alignment.TopEnd),
                            ) {
                                Icon(
                                    imageVector = (Icons.Filled.ArrowUpward),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .graphicsLayer(scaleY = 2.5f, scaleX = 1.5f)
                                )
                                Icon(
                                    imageVector = (Icons.Filled.ArrowDownward),
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .graphicsLayer(scaleY = 2.5f, scaleX = 1.5f)
                                )
                            }
                        }
                        Text("To")
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = Color.Transparent
                        )
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(20.dp),
                            color = Color.Gray
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        40.dp,
                        Alignment.CenterHorizontally
                    ),
                ) {
                    Text(
                        text = "Depature",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                    )
                    if (activeButton.value == 2 || activeButton.value == 3) {
                        VerticalDivider()
                        Text(
                            text = "Return",
                            color = Color.Gray,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = Color.Transparent
                        )
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(20.dp),
                            color = Color.Gray
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        40.dp,
                        Alignment.CenterHorizontally
                    ),
                ) {
                    Text(
                        text = "Class",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(vertical = 20.dp)
                    )
                    VerticalDivider()
                    Text(
                        text = "Travellers",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanBlue,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "Search Flight",
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview(){
    BookingsScreen(navController = rememberNavController())
}