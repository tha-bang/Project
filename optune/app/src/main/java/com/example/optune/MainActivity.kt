package com.example.optune

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.optune.education.EducationPage
import com.example.optune.education.HighSchoolPageForm
import com.example.optune.education.NoEducationPageForm
import com.example.optune.education.TertiaryPageForm
import com.example.optune.ui.screens.BusinessSignUpScreen
import com.example.optune.ui.screens.DashboardScreen
import com.example.optune.ui.screens.SignInScreen
import com.example.optune.ui.screens.SignUpFormScreen
import com.example.optune.ui.screens.SkillsAndInterestsScreen
import com.example.optune.ui.screens.SplashScreen
import com.example.optune.ui.screens.StudentSignUpScreen
import com.example.optune.ui.screens.UnemployedSignUpScreen
import com.example.optune.ui.theme.OptuneTheme
import com.example.optune.viewmodel.OfferViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()

        setContent {
            OptuneTheme {
                val navController = rememberNavController()
                val context = LocalContext.current


                val showToast: (String) -> Unit = { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }

                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash_screen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("splash_screen") {
                            SplashScreen(navController)
                        }
                        composable("home") {
                            HomePage(navController, showToast)
                        }
                        composable("signIn") {
                            SignInScreen(navController)
                        }
                        composable("signUp") {
                            SignUpFormScreen(navController)
                        }
                        composable("studentSignUp") {
                            StudentSignUpScreen(navController)
                        }
                        composable("unemployedSignUp") {
                            UnemployedSignUpScreen(navController)
                        }
                        composable("businessSignUp") {
                            BusinessSignUpScreen(navController)
                        }
                        composable("skillsAndInterests") {
                            SkillsAndInterestsScreen(navController)
                        }
                        composable("education") {
                            EducationPage(navController)
                        }
                        composable("highSchool") {
                            HighSchoolPageForm(navController)
                        }
                        composable("tertiary") {
                            TertiaryPageForm(navController)
                        }
                        composable("noEducation") {
                            NoEducationPageForm(navController)
                        }
                        composable(
                            "dashboardscreen/{role}",
                            arguments = listOf(navArgument("role") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val role = backStackEntry.arguments?.getString("role") ?: "unknown"
                            val viewModel: OfferViewModel = hiltViewModel()
                            DashboardScreen(navController, role, viewModel)
                        }
                        composable(
                            "signInSuccess/{role}",
                            arguments = listOf(navArgument("role") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val role = backStackEntry.arguments?.getString("role") ?: "unknown"
                            navController.navigate("dashboardscreen/$role")
                        }
                        composable("companyDashboard") {
                            com.example.optune.ui.screens.company.CompanyDashboardScreen(navController)
                        }
                        composable("postOpportunity") {
                            com.example.optune.ui.screens.company.PostOpportunityScreen(navController)
                        }
                        composable("opportunityDetails/{opportunityId}", arguments = listOf(navArgument("opportunityId") { type = NavType.StringType })) {
                            val opportunityId = it.arguments?.getString("opportunityId")
                            com.example.optune.ui.screens.OpportunityDetailsScreen(navController, opportunityId)
                        }
                        composable("userApplications") {
                            com.example.optune.ui.screens.UserApplicationsScreen()
                        }
                        composable("messages") {
                            com.example.optune.ui.screens.MessagesScreen(navController)
                        }
                        composable("chat") {
                            com.example.optune.ui.screens.ChatScreen()
                        }
                        composable("communityForum") {
                            com.example.optune.ui.screens.CommunityForumScreen(navController)
                        }
                        composable("userProfile") {
                            com.example.optune.ui.screens.UserProfileScreen(navController)
                        }
                        composable("forgotPassword") {
                            com.example.optune.ui.screens.ForgotPasswordScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController, showToast: (String) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
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
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to Optune!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Opportunities Delivered, Directly to You",
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { navController.navigate("signIn") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                    ) {
                        Text("Sign In", color = Color.White)
                    }
                    Button(
                        onClick = { navController.navigate("signUp") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                    ) {
                        Text("Sign Up", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    OptuneTheme {
        val previewShowToast: (String) -> Unit = { message ->
            println("Toast in preview: $message")
        }
        HomePage(rememberNavController(), previewShowToast)
    }
}

fun getCurrentUserId(): String? {
    return FirebaseAuth.getInstance().currentUser?.uid
}
