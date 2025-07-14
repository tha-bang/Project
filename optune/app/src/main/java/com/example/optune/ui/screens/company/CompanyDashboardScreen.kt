package com.example.optune.ui.screens.company

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CompanyDashboardScreen(navController: NavController) {
    val opportunities = listOf("Software Engineer", "Product Manager", "UX Designer")

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { navController.navigate("postOpportunity") }) {
            Text("Post New Opportunity")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(opportunities) { opportunity ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text(text = opportunity, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}