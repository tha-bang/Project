package com.example.optune.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
//import androidx.credentials.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import androidx.credentials.exceptions.GetCredentialException
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.optune.R
import com.example.optune.viewmodel.SignInViewModel
import com.example.optune.utils.StringConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val credentialManager = remember { CredentialManager.create(context) }
    val firebaseAuth = remember { FirebaseAuth.getInstance() }

    val handleGoogleSignIn = {
        coroutineScope.launch {
            val request = viewModel.createGoogleSignInRequest()

            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context
                )

                val credential = result.credential
                if (credential is GoogleIdTokenCredential) {
                    val googleIdToken = credential.idToken
                    val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

                    firebaseAuth.signInWithCredential(firebaseCredential)
                        .addOnSuccessListener {
                            navController.navigate("dashboard") {
                                popUpTo("signIn") { inclusive = true }
                            }
                        }
                        .addOnFailureListener { e ->
                            // Handle sign-in failure
                        }
                } else {
                    // Handle other credential types or unexpected scenarios
                }

            } catch (e: GetCredentialException) {
                // Handle sign-in failure
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.w21),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.sign_in),
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.select_your_role),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoleRadioButton(StringConstants.STUDENT, selectedRole) { selectedRole = it }
                RoleRadioButton(StringConstants.UNEMPLOYED, selectedRole) { selectedRole = it }
                RoleRadioButton(StringConstants.BUSINESS, selectedRole) { selectedRole = it }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(id = R.string.email)) },
                modifier = Modifier.fillMaxWidth(),
                isError = emailError,
                supportingText = {
                    if (emailError) {
                        Text(
                            stringResource(id = R.string.email_required),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(id = R.string.password)) },
                modifier = Modifier.fillMaxWidth(),
                isError = passwordError,
                supportingText = {
                    if (passwordError) {
                        Text(
                            stringResource(id = R.string.password_required),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    emailError = email.isEmpty()
                    passwordError = password.isEmpty()

                    if (!emailError && !passwordError && selectedRole != null) {
                        navController.navigate("dashboard") {
                            popUpTo("signIn") { inclusive = true }
                        }
                    }
                },
                enabled = selectedRole != null
            ) {
                Text(stringResource(id = R.string.sign_in))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { handleGoogleSignIn() }) {
                Text("Sign in with Google")
            }
            TextButton(onClick = { navController.navigate("forgotPassword") }) {
                Text("Forgot Password?", color = Color.White)
            }
        }
    }
}

@Composable
fun RoleRadioButton(role: String, selectedRole: String?, onRoleSelected: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selectedRole == role,
            onClick = { onRoleSelected(role) },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = Color.White
            )
        )
        Text(text = role, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(rememberNavController())
}
