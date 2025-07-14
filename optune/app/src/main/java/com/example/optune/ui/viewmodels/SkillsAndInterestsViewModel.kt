package com.example.optune.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optune.data.repository.UserRepository
import com.example.optune.ui.models.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class SkillsAndInterestsViewModel @Inject constructor(
    userRepository: UserRepository,
) : ViewModel() {
    lateinit var userRepository: UserRepository
    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData
    init {
        this.userRepository = userRepository
    }

    fun updateUserSkillsAndInterests(userId: String, skills: List<String>, interests: List<String>) {
        viewModelScope.launch {
            userRepository.updateUserSkillsAndInterests(userId, skills, interests)
        }
    }
    fun getUserData(){
        viewModelScope.launch {
            _userData.value = userRepository.getUserData()
        }
    }
}