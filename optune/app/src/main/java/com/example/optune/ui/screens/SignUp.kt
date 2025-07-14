package com.example.optune.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.optune.data.model.Business
import com.example.optune.data.model.Student
import com.example.optune.data.model.User
import java.time.LocalDate
import java.util.Calendar
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpFormScreen(
    
    navController: NavHostController
) {
    var selectedRole by remember { mutableStateOf("") }

        // Background Image

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sign Up As",
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    selectedRole = "Student"
                    navController.navigate("studentSignUp")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Student")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    selectedRole = "Unemployed"
                    navController.navigate("unemployedSignUp")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Unemployed")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    selectedRole = "Business"
                    navController.navigate("businessSignUp")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Business")
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentSignUpScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var identificationNumber by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }

    fun extractBirthDateFromId(id: String) {
        if (id.length >= 6) {
            val yearPart = id.substring(0, 2).toInt()
            val month = id.substring(2, 4).toInt()
            val day = id.substring(4, 6).toInt()

            // Determine century (00-21 is 2000s, 22-99 is 1900s)
            val century = if (yearPart in 0..21) 2000 else 1900
            val fullYear = century + yearPart

            // Validate the date components
            if (month in 1..12 && day in 1..31) {
                birthDate = "$fullYear-$month-$day"
            } else {
                birthDate = "Invalid date"
            }
        } else {
            birthDate = ""
        }
    }
    LaunchedEffect(identificationNumber) {
        if (identificationNumber.length >= 6) {
            extractBirthDateFromId(identificationNumber)
        } else {
            birthDate = ""
        }
    }

    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                birthDate = "$year-${month + 1}-$day"
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("password", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = identificationNumber,
            onValueChange = { identificationNumber = it },
            label = { Text("RSA ID Number", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = birthDate,
            onValueChange = {}, // Make it uneditable
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
            Text(text = "Gender",
                color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            RadioButton(
                selected = gender == "Male",
                onClick = { gender = "Male" }
            )
            Text(text = "Male", modifier = Modifier.padding(end = 8.dp),color = Color.Black)
            RadioButton(
                selected = gender == "Female",
                onClick = { gender = "Female" }
            )
            Text(text = "Female", modifier = Modifier.padding(end = 8.dp),color = Color.Black)
            RadioButton(
                selected = gender == "Other",
                onClick = { gender = "Other" }
            )
            Text(text = "Other", modifier = Modifier.padding(end = 8.dp),color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (error.isNotEmpty()) {
            Text(text = error, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (!isValidSouthAfricanId(identificationNumber)) {
                    error = "Invalid South African ID Number"
                } else if (birthDate.isEmpty()) {
                    error = "Please select birth date"
                } else if (gender.isEmpty()) {
                    error = "Please select gender"
                } else {
                    val userId = UUID.randomUUID().toString()
                    val user = User(
                        userId = userId,
                        name = "$firstName $lastName",
                        email = email,
                        signUpMethod = "Standard"
                    )
                    val student = Student(
                        userId = userId,
                        firstname = firstName,
                        lastname = lastName,
                        password = password,
                        email = email,
                        username = username,
                        identificationNumber = identificationNumber,
                        birthDate = birthDate,
                        gender = gender
                    )
                    navController.navigate("education")
                    println("User Data: $user")
                    println("Student Data: $student")
                    error = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnemployedSignUpScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var identificationNumber by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                birthDate = "$year-${month + 1}-$day"
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name", color = Color.Black) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name", color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = identificationNumber,
            onValueChange = { identificationNumber = it },
            label = { Text("RSA ID Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { showDatePicker = true }) {
            Text(text = if (birthDate.isEmpty()) "Select Birth Date" else "Birth Date: $birthDate")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(text = "Gender",
                color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            RadioButton(
                selected = gender == "Male",
                onClick = { gender = "Male" }
            )
            Text(text = "Male", modifier = Modifier.padding(end = 8.dp),color = Color.White)
            RadioButton(
                selected = gender == "Female",
                onClick = { gender = "Female" }
            )
            Text(text = "Female", modifier = Modifier.padding(end = 8.dp),color = Color.White)
            RadioButton(
                selected = gender == "Other",
                onClick = { gender = "Other" }
            )
            Text(text = "Other", modifier = Modifier.padding(end = 8.dp),color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (error.isNotEmpty()) {
            Text(text = error, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (!isValidSouthAfricanId(identificationNumber)) {
                    error = "Invalid South African ID Number"
                } else if (birthDate.isEmpty()) {
                    error = "Please select birth date"
                } else if (gender.isEmpty()) {
                    error = "Please select gender"
                } else {
                    val userId = UUID.randomUUID().toString()
                    val user = User(
                        userId = userId,
                        name = "$firstName $lastName",
                        email = email,
                        signUpMethod = "Standard"
                    )
                    val student = Student(
                        userId = userId,
                        firstname = firstName,
                        lastname = lastName,
                        username = username,
                        identificationNumber = identificationNumber,
                        birthDate = birthDate,
                        gender = gender
                    )
                    navController.navigate("education")
                    println("User Data: $user")
                    println("Student Data: $student")
                    error = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessSignUpScreen(navController: NavHostController) {
    var companyName by remember { mutableStateOf("") }
    var contactPerson by remember { mutableStateOf("") }
    var industry by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var foundingDate by remember { mutableStateOf("") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            foundingDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Business Sign Up", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = { Text("Company Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contactPerson,
            onValueChange = { contactPerson = it },
            label = { Text("Contact Person") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = industry,
            onValueChange = { industry = it },
            label = { Text("Industry") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = foundingDate,
            onValueChange = {},
            label = { Text("Founding Date") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth().clickable {
                datePickerDialog.show()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val userId = UUID.randomUUID().toString()
                val business = Business(
                    userId = userId,
                    companyName = companyName,
                    contactPerson = contactPerson,
                    industry = industry,
                    email = email,
                    phone = phone
                )
                println("Business Data: $business")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}

// RSA ID validation function
fun isValidSouthAfricanId(idNumber: String): Boolean {
    if (idNumber.length != 13 || !idNumber.matches(Regex("\\d+"))) return false
    val year = idNumber.substring(0, 2).toInt()
    val month = idNumber.substring(2, 4).toInt()
    val day = idNumber.substring(4, 6).toInt()
    val century = if (year in 0..21) 2000 else 1900
    try {
        LocalDate.of(century + year, month, day)
    } catch (e: Exception) {
        return false
    }
    // Luhn checksum
    var sum = 0
    for (i in 0 until 12) {
        var digit = idNumber[i].digitToInt()
        if (i % 2 == 1) {
            digit *= 2
            if (digit > 9) digit -= 9
        }
        sum += digit
    }
    val checkDigit = idNumber[12].digitToInt()
    return (sum + checkDigit) % 10 == 0
}

