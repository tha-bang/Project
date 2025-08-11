package com.example.optune.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optune.data.model.Student
import com.example.optune.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class StudentSignUpViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    var firstname by mutableStateOf("")
    var lastname by mutableStateOf("")
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var identificationNumber by mutableStateOf("")
    var birthDate by mutableStateOf("")
    var gender by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun validateBasicInfo(): Boolean {
        if (firstname.isBlank() || lastname.isBlank() || username.isBlank()) {
            errorMessage = "Please fill in all required fields."
            return false
        }
        if (email.isBlank() || !email.contains("@")) {
            errorMessage = "Please enter a valid email."
            return false
        }
        if (password.length < 6) {
            errorMessage = "Password must be at least 6 characters."
            return false
        }
        errorMessage = ""
        return true
    }

    fun validatePersonalDetails(): Boolean {
        if (identificationNumber.length < 6) {
            errorMessage = "ID number is too short."
            return false
        }
        if (birthDate.isBlank() || birthDate == "Invalid date") {
            errorMessage = "Please enter a valid birth date."
            return false
        }
        if (gender.isBlank()) {
            errorMessage = "Please select a gender."
            return false
        }
        errorMessage = ""
        return true
    }

    fun saveStudent(userId: String, onComplete: (String?) -> Unit) {
        isLoading = true
        val student = Student(
            userId = userId,
            firstname = firstname,
            lastname = lastname,
            username = username,
            email = email,
            identificationNumber = identificationNumber,
            birthDate = birthDate,
            gender = gender,
            password = password
        )
        viewModelScope.launch {
            val studentId = studentRepository.saveStudent(student)
            isLoading = false
            onComplete(studentId)
        }
    }
}