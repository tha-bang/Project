package com.example.optune.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun UserProfileScreen(navController: NavController) {
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("User Profile")
        // TODO: Implement user profile details

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showDialog.value = true }) {
            Text("Delete Account")
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text("Delete Account") },
                text = { Text("Are you sure you want to delete your account? This action cannot be undone.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val user = FirebaseAuth.getInstance().currentUser
                            if (user != null) {
                                val userId = user.uid
                                // Delete user from Firestore
                                FirebaseFirestore.getInstance().collection("users").document(userId).delete()
                                    .addOnSuccessListener {
                                        // Delete user from Firebase Authentication
                                        user.delete()
                                            .addOnCompleteListener {
                                                if (it.isSuccessful) {
                                                    navController.navigate("signIn") {
                                                        popUpTo("userProfile") { inclusive = true }
                                                    }
                                                }
                                            }
                                    }
                            }
                            showDialog.value = false
                        }
                    ) {
                        Text("Delete", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}