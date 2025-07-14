package com.example.optune.ui.screens

// Ensure all necessary imports are present for DocumentSubmission and Composable parts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// 1. YOUR DocumentSubmission DATA CLASS DEFINITION
data class DocumentSubmission(
    val userId: String,
    val userName: String,
    val docType: String,
    val docUrl: String,
    var isVerified: Boolean = false
)

// 2. <<<< YOUR sampleSubmissions DEFINITION SHOULD BE HERE (TOP-LEVEL) >>>>
val sampleSubmissions: List<DocumentSubmission> = listOf(
    DocumentSubmission(
        userId = "user123",
        userName = "Alice Wonderland",
        docType = "Passport",
        docUrl = "https://example.com/alice_passport.pdf"
    ),
    DocumentSubmission(
        userId = "user456",
        userName = "Bob The Builder",
        docType = "Driver's License",
        docUrl = "https://example.com/bob_license.pdf",
        isVerified = true
    ),
    DocumentSubmission(
        userId = "user789",
        userName = "Charlie Chaplin",
        docType = "Utility Bill",
        docUrl = "https://example.com/charlie_bill.pdf"
    )
)

// 3. YOUR VerifyDocumentsScreen COMPOSABLE
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyDocumentsScreen(
    navController: NavController,
    submissions: List<DocumentSubmission> = sampleSubmissions // This line causes the error
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Document Verification") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(submissions) { doc ->
                DocumentCard(doc)
            }
        }
    }
}

// 4. YOUR DocumentCard COMPOSABLE
@Composable
fun DocumentCard(submission: DocumentSubmission) {
    var verified by remember { mutableStateOf(submission.isVerified) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("User: ${submission.userName}")
            Text("Document: ${submission.docType}")
            Text(
                text = "View Document",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
                        // Open document URL in browser
                        // In real app use Intent or PDF Viewer
                    }
                    .padding(vertical = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        verified = true
                        // TODO: send "verified" status to backend
                    },
                    enabled = !verified
                ) {
                    Text("Verify")
                }

                Button(
                    onClick = {
                        verified = false
                        // TODO: send "rejected" status to backend
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    enabled = verified
                ) {
                    Text("Reject")
                }
            }

            if (verified) {
                Text("✅ Verified", color = MaterialTheme.colorScheme.primary)
            } else {
                Text("❌ Not Verified", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}