package com.example.optune.education

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.optune.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationPage(
    navController: NavHostController
) {
    var selectedRole by remember { mutableStateOf("") }

        // Background Image
        Image(
            painter = painterResource(id = R.drawable.w21),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

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
                    selectedRole = "highSchool"
                    navController.navigate("highSchool")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("high School")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    selectedRole = "Tertiary"
                    navController.navigate("tertiary")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Tertiary")
            }
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    selectedRole = "HighSchoolAndTertiary"
                    navController.navigate("highSchoolAndTertiary")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("High School And Tertiary")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    selectedRole = "No Education"
                    navController.navigate("noEducation")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("No Education")
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HighSchoolPageForm(navController: NavController, ) {
    var highSchoolName by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    var graduated by remember { mutableStateOf("Yes") }
    var streetAddress by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var documents: MutableList<Uri> = remember { mutableStateListOf() }
    val pickDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri>? ->
            uris?.let {
                documents.addAll(it)
            }
        }
    )
    Image(
        painter = painterResource(id = R.drawable.w21),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(text = "High School Information", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = highSchoolName,
            onValueChange = { highSchoolName = it },
            label = { Text("High School Name", color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = streetAddress,
            onValueChange = { streetAddress = it },
            label = { Text("Street Address", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state,
            onValueChange = { state = it },
            label = { Text("State", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = postalCode,
            onValueChange = { postalCode = it },
            label = { Text("Postal Code", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Graduated", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = graduated == "Yes",
                onClick = { graduated = "Yes" },
            )
            Text(text = "Yes", modifier = Modifier.padding(end = 8.dp), color = Color.White)
            RadioButton(
                selected = graduated == "No",
                onClick = { graduated = "No" }
            )
            Text(text = "No", modifier = Modifier.padding(end = 8.dp), color = Color.White)
        }

        if (graduated == "Yes") {
            Text(text = "Add Graduation Results Document", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = {
                pickDocumentLauncher.launch("*/*")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Graduation Results Document")
            }
        } else {
            Text(text = "Add Highest Grade Academic Report", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = {
                pickDocumentLauncher.launch("*/*")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Document")
            }
        }

        documents.forEach { uri ->
            Text(text = "Document: ${uri.lastPathSegment}", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Add CV", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        IconButton(onClick = {
            pickDocumentLauncher.launch("*/*")
        }) {
            Icon(Icons.Filled.Add, contentDescription = "Add CV")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (highSchoolName.isEmpty()) {
                    error = "Enter the High School name"
                } else if (streetAddress.isEmpty()) {
                    error = "Please enter the street Address"
                } else if (city.isEmpty()) {
                    error = "Please enter the field of study"
                } else if (documents.isEmpty()) {
                    error = "Please upload your CV or portfolio"
                }
                else if (state.isEmpty()) {
                    error = "Please enter the state"
                }
                else if (postalCode.isEmpty()) {
                    error = "Please enter the postal code"
                }
                else {
                    navController.navigate("skillsAndInterests")
                    error = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text("Continue ")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TertiaryPageForm(navController: NavController) {
    var tertiaryName by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    var graduated by remember { mutableStateOf("Yes") }
    var degree by remember { mutableStateOf("") }
    var fieldOfStudy by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var documents: MutableList<Uri> = remember { mutableStateListOf() }
    val pickDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri>? ->
            uris?.let {
                documents.addAll(it)
            }
        }
    )

    Image(
        painter = painterResource(id = R.drawable.w21),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(text = "Tertiary Education Information", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tertiaryName,
            onValueChange = { tertiaryName = it },
            label = { Text("Institution Name", color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = degree,
            onValueChange = { degree = it },
            label = { Text("Degree", color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fieldOfStudy,
            onValueChange = { fieldOfStudy = it },
            label = { Text("Field of Study", color = Color.White) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Graduated", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(
                selected = graduated == "Yes",
                onClick = { graduated = "Yes" },
            )
            Text(text = "Yes", modifier = Modifier.padding(end = 8.dp), color = Color.White)
            RadioButton(
                selected = graduated == "No",
                onClick = { graduated = "No" }
            )
            Text(text = "No", modifier = Modifier.padding(end = 8.dp), color = Color.White)
        }

        if (graduated == "Yes") {
            Text(text = "Add Academic Record Document", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            IconButton(onClick = {
                pickDocumentLauncher.launch("*/*")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Academic Record Document")
            }
        }

        documents.forEach { uri ->
            Text(text = "Document: ${uri.lastPathSegment}", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Add CV", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        IconButton(onClick = {
            pickDocumentLauncher.launch("*/*")
        }) {
            Icon(Icons.Filled.Add, contentDescription = "Add CV")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (tertiaryName.isEmpty()) {
                    error = "Enter the tertiary name"
                } else if (degree.isEmpty()) {
                    error = "Please enter the degree"
                } else if (fieldOfStudy.isEmpty()) {
                    error = "Please enter the field of study"
                } else if (documents.isEmpty()) {
                    error = "Please upload your CV or portfolio"
                } else {
                    navController.navigate("skillsAndInterests")
                    error = ""
                }
                },
                modifier = Modifier.fillMaxWidth()
                )
            {
            Text("Continue")
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoEducationPageForm(navController: NavController) {
    var documents: MutableList<Uri> = remember { mutableStateListOf() }
    var error by remember { mutableStateOf("") }
    val pickDocumentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris: List<Uri>? ->
            uris?.let {
                documents.addAll(it)
            }
        }
    )

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
        Text(text = "No Formal Education", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Add CV or Portfolio", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        IconButton(onClick = {
            pickDocumentLauncher.launch("*/*")
        }) {
            Icon(Icons.Filled.Add, contentDescription = "Add CV")
        }

        documents.forEach { uri ->
            Text(text = "Document: ${uri.lastPathSegment}", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (documents.isEmpty()) {
                    error = "Please upload your CV or portfolio"
                } else {
                    navController.navigate("skillsAndInterests")
                    error = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text("Continue ")
        }
    }
}

@Composable
fun HighSchoolAndTertiaryPageForm(navController: NavController) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        HighSchoolPageForm(navController)
        TertiaryPageForm(navController)
    }
}

// Preview functions
@Preview(showBackground = true)
@Composable
fun EducationPagePreview() {
    EducationPage(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun HighSchoolPageFormPreview() {
    val navController = rememberNavController()

    HighSchoolPageForm(navController)
}

@Preview(showBackground = true)
@Composable
fun TertiaryPageFormPreview() {
    val navController = rememberNavController()

    TertiaryPageForm(navController)
}

@Preview(showBackground = true)
@Composable
fun NoEducationPageFormPreview() {
    val navController = rememberNavController()

    NoEducationPageForm(navController)
}

@Preview(showBackground = true)
@Composable
fun HighSchoolAndTertiaryPageFormPreview() {
    val navController = rememberNavController()

    HighSchoolAndTertiaryPageForm(navController)
}