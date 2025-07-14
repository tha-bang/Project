package com.example.optune.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.optune.R

// Categorized skills
val categorizedSkills = mapOf(
    "Technical" to listOf(
        "Programming (Kotlin, Java, Swift, Python, etc.)",
        "Web Development (HTML, CSS, JavaScript, React, Angular, etc.)",
        "Mobile App Development (Android, iOS, Flutter, React Native)",
        "Data Analysis (SQL, R, Python, Machine Learning)"
    ),
    "Creative" to listOf(
        "Design (UI/UX, Graphic Design, Illustration)",
        "Writing & Content Creation",
        "Video Editing & Production"
    ),
    "Business" to listOf(
        "Marketing (Digital Marketing, Social Media Marketing, SEO/SEM)",
        "Sales",
        "Finance & Accounting",
        "Project Management (Agile, Scrum, Waterfall)"
    ),
    "Interpersonal" to listOf(
        "Communication (Writing, Presentation, Public Speaking)",
        "Leadership & Management",
        "Customer Service"
    ),
    "Other" to listOf("Other")
)

// Categorized interests
val categorizedInterests = mapOf(
    "Technology" to listOf(
        "Programming",
        "Artificial Intelligence",
        "Gadgets & Electronics"
    ),
    "Arts & Culture" to listOf(
        "Visual Arts",
        "Music",
        "Literature",
        "Theater"
    ),
    "Sports & Fitness" to listOf(
        "Team Sports",
        "Individual Sports",
        "Fitness & Gym",
        "Outdoor Activities"
    ),
    "Other" to listOf("Other")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillsAndInterestsScreen(
    navController: NavController
) {
    var selectedSkills by remember { mutableStateOf(emptyList<String>()) }
    var selectedInterests by remember { mutableStateOf(emptyList<String>()) }
    var error by remember { mutableStateOf("") }
    var customSkill by remember { mutableStateOf("") }
    var customInterest by remember { mutableStateOf("") }
    var showSkillsDialog by remember { mutableStateOf(false) }
    var showInterestsDialog by remember { mutableStateOf(false) }

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
            verticalArrangement = Arrangement.Center // This centers the content vertically
        ) {
            // Content container with fixed width
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f) // Takes 90% of screen width
                    .padding(vertical = 24.dp), // Add vertical padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Skills & Interests",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Skills Section
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Skills",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Skills selection button
                    SelectionButton(
                        text = if (selectedSkills.isEmpty()) "Select your skills"
                        else "${selectedSkills.size} skills selected",
                        onClick = { showSkillsDialog = true }
                    )

                    // Selected skills chips
                    SelectedItemsChips(
                        items = selectedSkills.filter { it != "Other" },
                        onRemove = { skill -> selectedSkills = selectedSkills - skill }
                    )

                    if (selectedSkills.contains("Other")) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = customSkill,
                            onValueChange = { customSkill = it },
                            label = { Text("Specify Other Skill") },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = Color.White),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Interests Section
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Interests",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Interests selection button
                    SelectionButton(
                        text = if (selectedInterests.isEmpty()) "Select your interests"
                        else "${selectedInterests.size} interests selected",
                        onClick = { showInterestsDialog = true }
                    )

                    // Selected interests chips
                    SelectedItemsChips(
                        items = selectedInterests.filter { it != "Other" },
                        onRemove = { interest -> selectedInterests = selectedInterests - interest }
                    )

                    if (selectedInterests.contains("Other")) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = customInterest,
                            onValueChange = { customInterest = it },
                            label = { Text("Specify Other Interest") },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = Color.White)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        val allSkills = selectedSkills.filter { it != "Other" } + listOfNotNull(
                            customSkill.takeIf { selectedSkills.contains("Other") && it.isNotBlank() }
                        )
                        val allInterests = selectedInterests.filter { it != "Other" } + listOfNotNull(
                            customInterest.takeIf { selectedInterests.contains("Other") && it.isNotBlank() }
                        )
                        if (allSkills.isEmpty()) {
                            error = "Please select at least one skill"
                        } else if (allInterests.isEmpty()) {
                            error = "Please select at least one interest"
                        } else {
                            navController.navigate("dashboard")
                        }
                    },
                    enabled = selectedSkills.isNotEmpty() || selectedInterests.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f) // Button takes 80% of parent width
                ) {
                    Text("Save & Continue to Dashboard")
                }

                // Display error message if any
                if (error.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

    // Skills Selection Dialog
    if (showSkillsDialog) {
        MultiSelectionDialog(
            title = "Select Skills",
            categorizedItems = categorizedSkills,
            selectedItems = selectedSkills,
            onConfirm = { selected ->
                selectedSkills = selected
                showSkillsDialog = false
            },
            onDismiss = { showSkillsDialog = false }
        )
    }

    // Interests Selection Dialog
    if (showInterestsDialog) {
        MultiSelectionDialog(
            title = "Select Interests",
            categorizedItems = categorizedInterests,
            selectedItems = selectedInterests,
            onConfirm = { selected ->
                selectedInterests = selected
                showInterestsDialog = false
            },
            onDismiss = { showInterestsDialog = false }
        )
    }
}
@Composable
fun SelectionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Button(
        onClick = onClick, // This onClick shows the dialog, not directly expanding this button
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text)
            // The expanded state for the icon here might be misleading if the button itself doesn't expand.
            // It currently reflects the dialog's visibility state indirectly.
            Icon(
                imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if (expanded) "Collapse" else "Expand"
            )
        }
    }
}

@Composable
fun SelectedItemsChips(
    items: List<String>,
    onRemove: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = "Selected:",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Consider a max height and scrollability if the list can be very long
            LazyColumn(modifier = Modifier.heightIn(max = 150.dp)) { // Example max height
                items(items) { item ->
                    SelectedChip(
                        text = item,
                        onRemove = { onRemove(item) }
                    )
                    HorizontalDivider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun SelectedChip(
    text: String,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "×",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .clickable { onRemove() }
                .padding(start = 8.dp)
        )
    }
}

@Composable
fun MultiSelectionDialog(
    title: String,
    categorizedItems: Map<String, List<String>>,
    selectedItems: List<String>,
    onConfirm: (List<String>) -> Unit,
    onDismiss: () -> Unit
) {
    val currentSelected = remember { mutableStateOf(selectedItems.toMutableList()) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false) // Good for custom dialog sizing
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f) // Use a fraction of screen width
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp), // Add some rounding to the card
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2C2C2C) // Slightly lighter dark color for the dialog
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier
                        .weight(1f, fill = false) // fill=false if content is smaller than available space
                        .fillMaxWidth()
                        .heightIn(max = 400.dp) // Set a max height for the list within the dialog
                ) {
                    categorizedItems.forEach { (category, items) ->
                        item {
                            Text(
                                text = category,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium, // Make category stand out
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
                            )
                        }

                        items(items) { item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp)) // Clip for rounded corners on clickable area
                                    .clickable {
                                        val mutableList = currentSelected.value.toMutableList()
                                        if (mutableList.contains(item)) {
                                            mutableList.remove(item)
                                        } else {
                                            mutableList.add(item)
                                        }
                                        currentSelected.value = mutableList
                                    }
                                    .padding(vertical = 10.dp, horizontal = 4.dp) // Increased padding
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                        .background(
                                            color = if (currentSelected.value.contains(item)) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                Color.Transparent // Checkbox background
                                            },
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .border( // Corrected: Added .border modifier
                                            width = 2.dp,
                                            color = if (currentSelected.value.contains(item)) {
                                                MaterialTheme.colorScheme.primary
                                            } else {
                                                Color.Gray // Checkbox border
                                            },
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                )

                                Spacer(modifier = Modifier.width(12.dp)) // Increased spacer

                                Text(
                                    text = item,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge // Slightly larger text
                                )
                            }
                        }
                        item { Spacer(modifier = Modifier.height(8.dp)) } // Spacer after each category's items
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp) // Increased padding
                ) {
                    Button(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent, // Or a subtle color
                            contentColor = MaterialTheme.colorScheme.primary // Use primary for text
                        )
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = { onConfirm(currentSelected.value.toList()) }, // Ensure immutable list
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000) // Dark background for preview
@Composable
fun SkillsAndInterestsScreenPreview() {

    SkillsAndInterestsScreen(
        navController = rememberNavController()
    )

}