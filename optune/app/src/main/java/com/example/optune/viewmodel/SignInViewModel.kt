package com.example.optune.viewmodel

import androidx.lifecycle.ViewModel
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    // You need to replace "YOUR_SERVER_CLIENT_ID" with your actual Web Client ID
    // from Google Cloud Console for your Firebase project.
    // This is crucial for Google Sign-In to work correctly.
    // TODO: Replace with your actual Web Client ID from Google Cloud Console
    private val serverClientId = "REPLACE_WITH_YOUR_WEB_CLIENT_ID"

    /**
     * Creates a request object for signing in with Google.
     * This request will be used with CredentialManager.
     */
    fun createGoogleSignInRequest(): GetCredentialRequest {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false) // Set to true to only show accounts already used with your app
            .setServerClientId(serverClientId)
            .setAutoSelectEnabled(false) // Set to true to attempt auto sign-in without user prompt if possible
            // .setNonce(YOUR_NONCE) // Optional: If you need a nonce for replay protection, generate and set it here
            .build()

        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    // You might add other SignIn related logic here, for example:
    // - Handling traditional email/password sign-in
    // - Managing UI state for the sign-in screen (e.g., loading, error messages)
    // - Validating input fields
}
