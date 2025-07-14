package com.example.optune.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.optune.data.model.Offer
import com.example.optune.viewmodel.OfferViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    role: String,
    viewModel: OfferViewModel
) {
    val filteredOffers by viewModel.filteredOffers.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Optune Dashboard") },
                actions = {
                    IconButton(onClick = { navController.navigate("notifications") }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                    IconButton(onClick = { navController.navigate("userProfile") }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome, $role",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                label = { Text("Search Opportunities") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = { /* TODO: Implement Location Filter */ }) { Text("Location") }
                Button(onClick = { /* TODO: Implement Job Type Filter */ }) { Text("Job Type") }
                Button(onClick = { /* TODO: Implement Salary Filter */ }) { Text("Salary") }
                Button(onClick = { /* TODO: Implement Opportunity Type Filter */ }) { Text("Opportunity Type") }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("communityForum") }) {
                Text("Community Forum")
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (role.lowercase()) {
                "student", "unemployed" -> {
                    Text("Recommended Opportunities:")
                    OfferList(offers = filteredOffers) { selected ->
                        navController.navigate("opportunityDetails/${selected.id}")
                    }
                }

                "business" -> {
                    navController.navigate("companyDashboard")
                }

                "admin" -> {
                    Button(
                        onClick = { navController.navigate("verifyDocuments") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Verify Documents")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Latest Posted Opportunities:")
                    OfferList(offers = filteredOffers) {}
                }

                else -> {
                    Text("Role not recognized.")
                }
            }
        }
    }
}

@Composable
fun OfferList(offers: List<Offer>, onClick: (Offer) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(offers) {
            offer ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick(offer) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = offer.title, style = MaterialTheme.typography.titleLarge)
                    Text(text = offer.description, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "From: ${offer.businessName}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
