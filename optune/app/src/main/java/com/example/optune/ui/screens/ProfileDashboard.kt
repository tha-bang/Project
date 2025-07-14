package com.example.optune.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.runtime.internal.ComposableFunction1
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.optune.R
import com.example.optune.ui.models.UserData

@Composable
fun ProfileDashboardScreen(navController: NavController, userData: UserData) {
    Image(
        painter = painterResource(id = R.drawable.w21),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${userData.name}", fontSize = 24.sp, color = Color.Black)
        Text(text = "Email: ${userData.email}", fontSize = 16.sp, color = Color.Gray)
        Text(text = "Skills: ${userData.skills.joinToString(", ")}", fontSize = 16.sp, color = Color.Gray)
        Text(text = "Interests: ${userData.interests.joinToString(", ")}", fontSize = 16.sp, color = Color.Gray)
        Text(text = "Progress: ${userData.progress}", fontSize = 16.sp, color = Color.Gray)
        Text(text = "Courses: ${userData.coursesTaken}", fontSize = 16.sp, color = Color.Gray)
        Text(text = "Joined: ${userData.formattedJoinDate()}", fontSize = 16.sp, color = Color.Gray)
    }
}