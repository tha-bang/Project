package com.example.optune.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OpportunityDetailsScreen(navController: NavController, opportunityId: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Opportunity Details for ID: $opportunityId")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* TODO: Implement apply logic */ }) {
            Text("Apply Now")
        }
    }
}