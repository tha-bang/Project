package com.example.optune.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optune.data.model.Business
import com.example.optune.data.model.Student
import com.example.optune.data.model.Unemployed
import com.example.optune.data.model.User
import com.example.optune.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    // Compose-observable fields for the Business sign-up flow
    var companyName by mutableStateOf("")
    var industry by mutableStateOf("")
    var foundingDate by mutableStateOf("")
    var contactPerson by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")

    // existing functions for Student/Unemployed if you still need them:
    fun createStudent(student: Student, onComplete: (String?) -> Unit) {
        viewModelScope.launch {
            val userId = userRepository.createStudent(student)
            onComplete(userId)
        }
    }

    fun createUnemployed(unemployed: Unemployed, onComplete: (String?) -> Unit) {
        viewModelScope.launch {
            val userId = userRepository.createUnemployed(unemployed)
            onComplete(userId)
        }
    }

    // Use the state fields to build the Business object
    fun createBusiness(onComplete: (String?) -> Unit) {
        val business = Business(
            companyName = companyName,
            industry = industry,
            foundingDate = foundingDate,
            contactPerson = contactPerson,
            email = email,
            phone = phone
        )

        viewModelScope.launch {
            val userId = userRepository.createBusiness(business)
            onComplete(userId)
        }
    }

    fun saveUser(user: User, userId: String, onComplete: (String?) -> Unit) {
        viewModelScope.launch {
            val savedUserId = userRepository.saveUser(user, userId)
            onComplete(savedUserId)
        }
    }
}
