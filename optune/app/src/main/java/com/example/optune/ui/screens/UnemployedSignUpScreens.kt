package com.example.optune.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.optune.R
import com.example.optune.viewmodel.UnemployedSignUpViewModel
import com.example.optune.data.model.User
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnemployedSignUpScreen(navController: NavHostController, viewModel: UnemployedSignUpViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.firstname,
            onValueChange = { viewModel.firstname = it },
            label = { Text("First Name", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.lastname,
            onValueChange = { viewModel.lastname = it },
            label = { Text("Last Name", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.username,
            onValueChange = { viewModel.username = it },
            label = { Text("Username", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Password", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (viewModel.errorMessage.isNotEmpty()) {
            Text(text = viewModel.errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (viewModel.validateBasicInfo()) {
                    navController.navigate("unemployedPersonalDetails") {
                        launchSingleTop = true
                    }
                }
            },
            enabled = !viewModel.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnemployedPersonalDetailsScreen(navController: NavHostController, viewModel: UnemployedSignUpViewModel = hiltViewModel()) {
    var profilePictureUri by remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        profilePictureUri = uri?.toString()
        viewModel.profilePictureUri = uri?.toString() ?: ""
    }

    fun extractBirthDateFromId(id: String) {
        if (id.length >= 6) {
            try {
                val yearPart = id.substring(0, 2).toInt()
                val month = id.substring(2, 4).toInt()
                val day = id.substring(4, 6).toInt()
                val century = if (yearPart in 0..21) 2000 else 1900
                val fullYear = century + yearPart
                val date = LocalDate.of(fullYear, month, day)
                viewModel.birthDate = date.toString()
            } catch (_: Exception) {
                viewModel.birthDate = "Invalid date"
            }
        } else {
            viewModel.birthDate = ""
        }
    }

    LaunchedEffect(viewModel.identificationNumber) {
        if (viewModel.identificationNumber.length >= 6) {
            extractBirthDateFromId(viewModel.identificationNumber)
        } else {
            viewModel.birthDate = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.identificationNumber,
            onValueChange = { viewModel.identificationNumber = it },
            label = { Text("RSA ID Number", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.birthDate,
            onValueChange = {},
            label = { Text("Date of Birth", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            enabled = false
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Gender", color = Color.Black)
            RadioButton(
                selected = viewModel.gender == "Male",
                onClick = { viewModel.gender = "Male" }
            )
            Text(text = "Male", modifier = Modifier.padding(end = 8.dp), color = Color.Black)
            RadioButton(
                selected = viewModel.gender == "Female",
                onClick = { viewModel.gender = "Female" }
            )
            Text(text = "Female", modifier = Modifier.padding(end = 8.dp), color = Color.Black)
            RadioButton(
                selected = viewModel.gender == "Other",
                onClick = { viewModel.gender = "Other" }
            )
            Text(text = "Other", modifier = Modifier.padding(end = 8.dp), color = Color.Black)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Profile Picture", color = Color.Black)
            IconButton(onClick = { launcher.launch("image/*") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Profile Picture")
            }
        }
        profilePictureUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        } ?: run {
            Image(
                painter = painterResource(id = R.drawable.ic_profile_placeholder),
                contentDescription = "Profile Picture Placeholder",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.bio,
            onValueChange = { viewModel.bio = it },
            label = { Text("Short Bio", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (viewModel.errorMessage.isNotEmpty()) {
            Text(text = viewModel.errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (viewModel.validatePersonalDetails()) {
                    navController.navigate("unemployedContactDetails") {
                        launchSingleTop = true
                    }
                }
            },
            enabled = !viewModel.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnemployedContactDetailsScreen(navController: NavHostController, viewModel: UnemployedSignUpViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.phoneNumber,
            onValueChange = { viewModel.phoneNumber = it },
            label = { Text("Phone Number", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.streetAddress,
            onValueChange = { viewModel.streetAddress = it },
            label = { Text("Street Address", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.city,
            onValueChange = { viewModel.city = it },
            label = { Text("City", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.state,
            onValueChange = { viewModel.state = it },
            label = { Text("State", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.postalCode,
            onValueChange = { viewModel.postalCode = it },
            label = { Text("Postal Code", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (viewModel.errorMessage.isNotEmpty()) {
            Text(text = viewModel.errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (viewModel.validateContactDetails()) {
                    viewModel.createUnemployed { userId ->
                        if (userId != null) {
                            val user = User(
                                id = userId,
                                name = "${viewModel.firstname} ${viewModel.lastname}",
                                email = viewModel.email
                                // Add more fields if your User model requires
                            )
                            viewModel.saveUser(user, userId) { savedUserId ->
                                if (savedUserId != null) {
                                    navController.navigate("skillsAndInterests?userId=$savedUserId") {
                                        popUpTo("unemployedSignUp") { inclusive = true }
                                    }
                                }
                            }
                        }
                    }
                }
            },
            enabled = !viewModel.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finish")
        }
    }
}