package com.airline.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airline.R


@Composable
fun LoginScreen(){
    var userName by rememberSaveable { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal= 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            painter = painterResource(R.drawable.icon),
            contentDescription = "App Icon"
        )
        Column(
            modifier = Modifier
                .weight(2f)
                .border(width= 1.dp, shape= RectangleShape, color= Color.Red),
        ) {
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },

//            leadingIcon = Icons.Filled.AccountCircle
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },

//            leadingIcon = Icons.Filled.AccountCircle
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                trailingIcon = {
                    val image = if(passwordVisibility)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff
                    val description= if(passwordVisibility) "Hide password" else "Show password"
                    IconButton(
                        onClick = {passwordVisibility = !passwordVisibility}
                    ){
                        Icon(imageVector= image, contentDescription = description)
                    }
                },
                visualTransformation = if(passwordVisibility)
                    VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}