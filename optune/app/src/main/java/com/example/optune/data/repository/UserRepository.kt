package com.example.optune.data.repository

import com.example.optune.data.model.Business
import com.example.optune.data.model.Student
import com.example.optune.data.model.Unemployed
import com.example.optune.data.model.User
import com.example.optune.data.remote.UserDataSource
import com.example.optune.ui.models.UserData
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDataSource: UserDataSource) {
    fun updateUserSkillsAndInterests(userId: String, skills: List<String>, interests: List<String>) {
        // Implement your logic to update user skills and interests
    }

    fun getUserData(): UserData {
        // Implement your logic to retrieve user data
        return UserData(
            name = "",
            email = "",
            profileImage = "",
            skills = emptyList(),
            interests = emptyList()
        )
    }


    suspend fun createStudent(student: Student): String? {
        return userDataSource.createStudent(student)
    }
    suspend fun createUnemployed(unemployed: Unemployed): String? {
        return userDataSource.createUnemployed(unemployed)
    }
    suspend fun createBusiness(business: Business): String? {
        return userDataSource.createBusiness(business)
    }
    susend fun saveUser(user: User, userId: String):String?{
        return userDataSource.saveUser(user, userId)
    }
}