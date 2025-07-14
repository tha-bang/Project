package com.example.optune.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.optune.education.EducationPage
import com.example.optune.education.HighSchoolAndTertiaryPageForm
import com.example.optune.education.HighSchoolPageForm
import com.example.optune.education.NoEducationPageForm
import com.example.optune.education.TertiaryPageForm
import com.example.optune.ui.screens.DashboardScreen
import com.example.optune.ui.screens.SignInScreen
import com.example.optune.ui.screens.SignUpFormScreen
import com.example.optune.ui.screens.SkillsAndInterestsScreen
import com.example.optune.viewmodel.OfferViewModel

@Composable
fun AppNavGraph(
    showToast: (String) -> Unit,
    offerViewModel: OfferViewModel,
    userRole: String = "unemployed", // Default role; replace with real user data
    isSignedIn: Boolean = false
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isSignedIn) Route.DASHBOARD else Route.SIGN_IN
    ) {
        // Auth Screens
        composable(Route.SIGN_IN) {
            SignInScreen(navController = navController)
        }
        composable(Route.SIGN_UP) {
            SignUpFormScreen(navController = navController)
        }

        // Dashboard
        composable(Route.DASHBOARD) {
            DashboardScreen(
                navController = navController,
                role = userRole,
                viewModel = offerViewModel
            )
        }

        // Onboarding / Setup
        composable(Route.SKILLS_AND_INTERESTS) {
            SkillsAndInterestsScreen(navController = navController)
        }
        composable(Route.EDUCATION) {
            EducationPage(navController = navController)
        }
        composable(Route.HIGH_SCHOOL) {
            HighSchoolPageForm(navController = navController)
        }
        composable(Route.TERTIARY) {
            TertiaryPageForm(navController = navController)
        }
        composable(Route.NO_EDUCATION) {
            NoEducationPageForm(navController = navController)
        }
        composable(Route.HIGH_SCHOOL_AND_TERTIARY) {
            HighSchoolAndTertiaryPageForm(navController = navController)
        }
    }
}
