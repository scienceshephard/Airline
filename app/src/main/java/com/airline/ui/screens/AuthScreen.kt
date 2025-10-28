package com.airline.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airline.R
import com.airline.ui.theme.Typography


@Composable
fun SignupScreen(
    navController: NavHostController

){
    var userName by rememberSaveable { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val annotatedText = buildAnnotatedString {
        append("Already have an account?")
        pushStringAnnotation(tag = "click_here", annotation = "here_clicked")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ){
            append("Log in")
        }
        pop()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal= 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            painter = painterResource(R.drawable.icon),
            contentDescription = "App Icon"
        )
        Text(
            text = "Sign up",
            style = Typography.headlineLarge
        )
        Column(
            modifier = Modifier
                .weight(2f),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column {
                Text("USERNAME", )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = userName,
                    onValueChange = { userName = it },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            "Account"
                        )
                    },
                )
            }
            Column{
                Text("EMAIL ADDRESS")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            "Email"
                        )
                    }
                )
            }
            Column {
                Text("PASSWORD")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            "password"
                        )
                    },
                    trailingIcon = {
                        val image = if (passwordVisibility)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff
                        val description =
                            if (passwordVisibility) "Hide password" else "Show password"
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility }
                        ) {
                            Icon(imageVector = image, contentDescription = description)
                        }
                    },
                    visualTransformation = if (passwordVisibility)
                        VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
            }
            MyButton(
                click = {
                    navController.navigate(Screen.MainGraph.route){
                        popUpTo(Screen.Signup.route){inclusive = true}
                    }
                },
                btnText = "Sign up",
            )
            Row(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f),
                    thickness = 1.dp
                )

                Text(
                    text = "OR",
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f),
                    thickness = 1.dp
                )
            }

            Column(
            ) {
                Button(
                    onClick = {
                        //Google auth
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "google image",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text("Continue with Google")
                    }
                }
                Text(
                    text = annotatedText,
                    modifier = Modifier
                        .clickable{
//                            Navigate to Login page
                            navController.navigate(Screen.Login.route)
                        }
                        .fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController
){
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val annotatedText = buildAnnotatedString {
        append("Don't have an account?")
        pushStringAnnotation(tag = "click_here", annotation = "here_clicked")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ){
            append("Sign up")
        }
        pop()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal= 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            painter = painterResource(R.drawable.icon),
            contentDescription = "App Icon"
        )
        Text(
            text = "Log in",
            style = Typography.headlineLarge
        )
        Column(
            modifier = Modifier
                .weight(2f),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column{
                Text("EMAIL ADDRESS")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            "Email"
                        )
                    }
                )
            }
            Column {
                Text("PASSWORD")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            "password"
                        )
                    },
                    trailingIcon = {
                        val image = if (passwordVisibility)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff
                        val description =
                            if (passwordVisibility) "Hide password" else "Show password"
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility }
                        ) {
                            Icon(imageVector = image, contentDescription = description)
                        }
                    },
                    visualTransformation = if (passwordVisibility)
                        VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )
            }
            MyButton(
                click = {
                    navController.navigate(Screen.MainGraph.route)
                },
                btnText = "Log in",
            )
            Row(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f),
                    thickness = 1.dp
                )

                Text(
                    text = "OR",
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f),
                    thickness = 1.dp
                )
            }

            Column(
            ) {
                Button(
                    onClick = {
                        //Google auth
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "google image",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text("Continue with Google")
                    }
                }
                Text(
                    text = annotatedText,
                    modifier = Modifier
                        .clickable{
//                            Navigate to Login page

                        }
                        .fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview( showBackground = false, showSystemUi = true  )
@Composable
fun LoginScreenPreview(){
}