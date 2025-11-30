package com.airline.ui.screens.main_screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.airline.R
import com.airline.ui.theme.CyanBlue
import com.airline.ui.theme.Typography

@Composable
fun SearchButton(){
    var searchQuery by remember { mutableStateOf("") }
    Row(
        modifier = Modifier

    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text("Searching...")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search button"
                )
            },
            trailingIcon = {
                if(searchQuery.isNotEmpty()){
                    IconButton(
                        onClick = {
                            searchQuery = ""
                        }
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            shape = MaterialTheme.shapes.large
        )
    }
}

@Composable
fun HomeScreen(){
    val focusManager = LocalFocusManager.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .clickable(
                indication = null,
                interactionSource = remember{ MutableInteractionSource() }
            ){
                focusManager.clearFocus()
            },
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        item{
            SearchButton()
        }
        item{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(R.drawable.airplane),
                    contentDescription = "airplane",
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .matchParentSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "FLY", style = Typography.titleLarge, color = Color.White)
                    Text(text = "HIGH WITH", style = Typography.titleLarge, color = Color.White)
                    Text(text = "SKY JOURNEY", style = Typography.titleLarge, color = Color.White)
                }
                Image(
                    painter = painterResource(R.drawable.splash_image),
                    contentDescription = "icon",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(100.dp),
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .offset(y = (20.dp))
                        .fillMaxWidth()
                        .zIndex(2f)
                        .align(Alignment.BottomEnd),
                ){
                    Text(
                        text = "Book Flight",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = CyanBlue,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 20.dp)
                    )
                    Text(
                        text = "Sky AI",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = CyanBlue,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(20.dp)
                    )
                }
            }
        }
        item{
            Spacer(modifier = Modifier
                .size(10.dp))
        }
        item{
            Text("Popular Destination")
        }
        item{
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Local Flight"
                )
                Text(
                    text = "See all",
                    color = CyanBlue
                )
            }
        }
        item{
            Local()
        }
        item{
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "International Flight"
                )
                Text(
                    text = "See all",
                    color = CyanBlue
                )
            }
        }
        item{
            International()
        }
    }
}
data class Destination(
    val country: String = "Nigeria",
    val city: String,
    val image_url: Int
)
@Composable
fun Local(){
    val destinations = listOf(
        Destination(city = "Lagos", image_url = R.drawable.lagos),
        Destination(city = "Abuja", image_url = R.drawable.abuja),
        Destination(city = "Calabar", image_url = R.drawable.calabar)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(destinations){ dest ->
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillParentMaxWidth(0.6f),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Box{
                    Image(
                        painter = painterResource(id = dest.image_url),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = dest.country,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(text = dest.city, color = Color.White, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun International(){
    val destinations = listOf(
        Destination(city = "Paris", image_url = R.drawable.lagos, country = "France"),
        Destination(city = "New York", image_url = R.drawable.usa, country = "USA"),
        Destination(city = "Hong Kong", image_url = R.drawable.china, country = "China")
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(destinations){ dest ->
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillParentMaxWidth(0.6f),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Box{
                    Image(
                        painter = painterResource(id = dest.image_url),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = dest.country,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(text = dest.city, color = Color.White, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview(device= "id:pixel_6", showSystemUi = true)
@Composable
fun HomePrev(){
    HomeScreen()
}
