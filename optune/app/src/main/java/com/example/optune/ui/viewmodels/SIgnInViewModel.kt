package com.example.optune.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val _isSignedIn = MutableStateFlow(false) // Initial state: not signed in
    val isSignedIn: StateFlow<Boolean> = _isSignedIn

    // Simulate a sign-in process
    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            // In a real app, you'd call your repository here to authenticate
            // ... (authentication logic)

            // Assuming successful sign-in
            _isSignedIn.value = true
        }
    }

    // Method to sign out
    fun signOut() {
        _isSignedIn.value = false
    }
}