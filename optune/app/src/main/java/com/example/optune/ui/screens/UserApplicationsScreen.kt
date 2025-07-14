package com.example.optune.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserApplicationsScreen() {
    val applications = listOf("Software Engineer - Applied", "Product Manager - Interviewing", "UX Designer - Accepted")

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(applications) { application ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(text = application, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}