package com.example.optune.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.optune.education.EducationPage
import com.example.optune.education.HighSchoolAndTertiaryPageForm
import com.example.optune.education.HighSchoolPageForm
import com.example.optune.education.NoEducationPageForm
import com.example.optune.education.TertiaryPageForm
import com.example.optune.ui.screens.DashboardScreen
import com.example.optune.ui.screens.SignInScreen
import com.example.optune.ui.screens.SignUpFormScreen
import com.example.optune.ui.screens.SkillsAndInterestsScreen
import com.example.optune.ui.screens.StudentPersonalDetailsScreen
import com.example.optune.ui.screens.StudentContactDetailsScreen
import com.example.optune.ui.screens.UnemployedPersonalDetailsScreen
import com.example.optune.ui.screens.UnemployedContactDetailsScreen
import com.example.optune.ui.screens.StudentSignUpScreen
import com.example.optune.ui.screens.UnemployedSignUpScreen
import com.example.optune.viewmodel.SignUpViewModel
import com.example.optune.data.repository.UserRepository
import com.example.optune.data.remote.UserDataSource
import com.example.optune.di.ViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.optune.ui.screens.BusinessContactDetailsScreen
import com.example.optune.viewmodel.OfferViewModel

@Composable
fun AppNavGraph(
    showToast: (String) -> Unit,
    offerViewModel: OfferViewModel,
    userRole: String = "unemployed", // Default role; replace with real user data
    isSignedIn: Boolean = false
) {
    val userRepository = UserRepository(UserDataSource())
    val viewModelFactory = ViewModelFactory(userRepository)
    val signUpViewModel: SignUpViewModel = viewModel(factory = viewModelFactory)

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

        composable(Route.STUDENT_SIGN_UP) {
            StudentSignUpScreen(navController = navController)
        }

        composable(Route.UNEMPLOYED_SIGN_UP) {
            UnemployedSignUpScreen(navController = navController, signUpViewModel = signUpViewModel)
        }

        composable(Route.BUSINESS_SIGN_UP) {
            BusinessSignUpScreen(navController = navController, signUpViewModel = signUpViewModel)
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
        composable(
            route = Route.EDUCATION,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            EducationPage(navController = navController, userId = userId)
        }
        composable(
            route = Route.HIGH_SCHOOL,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            HighSchoolPageForm(navController = navController, userId = userId)
        }
        composable(
            route = Route.TERTIARY,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            TertiaryPageForm(navController = navController, userId = userId)
        }
        composable(
            route = Route.NO_EDUCATION,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            NoEducationPageForm(navController = navController, userId = userId)
        }
        composable(
            route = Route.HIGH_SCHOOL_AND_TERTIARY,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            HighSchoolAndTertiaryPageForm(navController = navController, userId = userId)
        }

        composable(Route.STUDENT_PERSONAL_DETAILS) {
            StudentPersonalDetailsScreen(navController = navController, signUpViewModel = signUpViewModel)
        }

        composable(Route.STUDENT_CONTACT_DETAILS) {
            StudentContactDetailsScreen(navController = navController, signUpViewModel = signUpViewModel)
        }

        composable(Route.UNEMPLOYED_PERSONAL_DETAILS) {
            UnemployedPersonalDetailsScreen(navController = navController, signUpViewModel = signUpViewModel)
        }

        composable(Route.UNEMPLOYED_CONTACT_DETAILS) {
            UnemployedContactDetailsScreen(navController = navController, signUpViewModel = signUpViewModel)
        }

        composable(Route.BUSINESS_CONTACT_DETAILS) {
            BusinessContactDetailsScreen(navController = navController, signUpViewModel = signUpViewModel)
        }
    }
}
