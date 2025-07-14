package com.example.optune.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.optune.ui.viewmodels.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
    var selectedRole by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select Your Role", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Role Selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = selectedRole == "Student",
                    onClick = { selectedRole = "Student" }
                )
                Text("Student")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = selectedRole == "Unemployed",
                    onClick = { selectedRole = "Unemployed" }
                )
                Text("Unemployed")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = selectedRole == "Business",
                    onClick = { selectedRole = "Business" }
                )
                Text("Business")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showError && viewModel.errorMessage.isNotEmpty()) {
            Text(text = viewModel.errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                when (selectedRole) {
                    "Student" -> navController.navigate("studentSignUp")
                    "Unemployed" -> navController.navigate("unemployedSignUp")
                    "Business" -> navController.navigate("businessSignUp")
                    else -> {
                        viewModel.errorMessage = "Please select a role"
                        showError = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedRole.isNotEmpty()
        ) {
            Text("Continue")
        }
    }
}