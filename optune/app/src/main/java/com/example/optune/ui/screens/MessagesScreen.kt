package com.example.optune.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController

@Composable
fun MessagesScreen(navController: NavController) {
    val conversations = listOf("Google", "Microsoft", "Amazon")

    Column(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            items(conversations) { conversation ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable { navController.navigate("chat") }) {
                    Text(text = conversation, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}