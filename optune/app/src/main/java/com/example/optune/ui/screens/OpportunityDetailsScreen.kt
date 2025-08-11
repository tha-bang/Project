package com.example.optune.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun OpportunityDetailsScreen(navController: NavController, opportunityId: String?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Opportunity Details for ID: $opportunityId")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* TODO: Implement apply logic */ }) {
            Text("Apply Now")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            IconButton(onClick = { sendFeedback(opportunityId, "like") }) {
                Icon(Icons.Default.ThumbUp, contentDescription = "Like")
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { sendFeedback(opportunityId, "dislike") }) {
                Icon(Icons.Default.ThumbDown, contentDescription = "Dislike")
            }
        }
    }
}

private fun sendFeedback(opportunityId: String?, feedback: String) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    if (userId != null && opportunityId != null) {
        val feedbackData = hashMapOf(
            "userId" to userId,
            "opportunityId" to opportunityId,
            "feedback" to feedback
        )
        FirebaseFirestore.getInstance().collection("feedback").add(feedbackData)
    }
}