package com.example.optune.ui.viewmodels

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

    fun createBusiness(business: Business, onComplete: (String?) -> Unit) {
        viewModelScope.launch {
            val userId = userRepository.createBusiness(business)
            onComplete(userId)
        }
    }
    fun saveUser(user: User, userId:String, onComplete: (String?) -> Unit) {
        viewModelScope.launch {
            val savedUserId = userRepository.saveUser(user, userId)
            onComplete(savedUserId)
        }
    }

}