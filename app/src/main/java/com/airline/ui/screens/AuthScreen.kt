package com.airline.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(R.drawable.icon),
            contentDescription = "App Icon"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Sign up",
            style = Typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column {
                Text(
                    text = "USERNAME",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = userName,
                    onValueChange = { userName = it },
                    placeholder = {
                        Text(
                            text = "Enter Username",
                            color = Color.LightGray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            "Account",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Column{
                Text(
                    text = "EMAIL ADDRESS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            text = "Enter Email Address",
                            color = Color.LightGray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            "Email",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Column {
                Text(
                    text = "PASSWORD",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = "Create Password",
                            color = Color.LightGray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            "password",
                            tint = Color.Gray
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
                            Icon(
                                imageVector = image,
                                contentDescription = description,
                                tint = Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility)
                        VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            MyButton(
                click = {
                    navController.navigate(Screen.MainGraph.route){
                        popUpTo(Screen.Signup.route){inclusive = true}
                    }
                },
                btnText = "Sign up",
            )

            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                Text(
                    text = "OR",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.MainGraph.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "google image",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Continue with Google",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Text(
                    text = annotatedText,
                    modifier = Modifier
                        .clickable{
                            navController.navigate(Screen.Login.route)
                        }
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
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
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(R.drawable.icon),
            contentDescription = "App Icon"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Log in",
            style = Typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column{
                Text(
                    text = "" +
                            " ADDRESS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            text = "Enter Email Address",
                            color = Color.LightGray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Email,
                            "Email",
                            tint = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Column {
                Text(
                    text = "PASSWORD",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = "Enter Password",
                            color = Color.LightGray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Lock,
                            "password",
                            tint = Color.Gray
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
                            Icon(
                                imageVector = image,
                                contentDescription = description,
                                tint = Color.Gray
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility)
                        VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            MyButton(
                click = {
                    navController.navigate(Screen.MainGraph.route)
                },
                btnText = "Log in",
            )

            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                Text(
                    text = "OR",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.MainGraph.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "google image",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Continue with Google",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Text(
                    text = annotatedText,
                    modifier = Modifier
                        .clickable{
                            navController.navigate(Screen.Signup.route)
                        }
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
    LoginScreen(navController = rememberNavController())
}