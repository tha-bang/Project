package com.example.optune.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
    val offers by viewModel.offers.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Optune Dashboard") },
                actions = {
                    IconButton(onClick = { navController.navigate("notifications") }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                    IconButton(onClick = { navController.navigate("profile") }) {
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

            when (role.lowercase()) {
                "student", "unemployed" -> {
                    Text("Recommended Opportunities:")
                    OfferList(offers = offers) { selected ->
                        navController.navigate("offerDetail/${selected.id}")
                    }
                }

                "business" -> {
                    Button(
                        onClick = { navController.navigate("postOffer") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Post New Opportunity")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Your Current Offers:")
                    OfferList(offers = offers.filter { it.businessName == "YourBusinessName" }) {}
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
                    OfferList(offers = offers) {}
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
        items(offers) { offer ->
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

