package com.example.optune.data.remote

import com.example.optune.data.model.Business
import com.example.optune.data.model.Student
import com.example.optune.data.model.Unemployed
import com.example.optune.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserDataSource {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun createStudent(student: Student): String? {
        return try {
            val documentReference = firestore.collection("students").add(student).await()
            documentReference.id
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun createUnemployed(unemployed: Unemployed): String? {
        return try {
            val documentReference = firestore.collection("unemployed").add(unemployed).await()
            documentReference.id
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun createBusiness(business: Business): String? {
        return try {
            val documentReference = firestore.collection("business").add(business).await()
            documentReference.id
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    suspend fun saveUser(user: User, userId:String):String?{
        return try {
            firestore.collection("users").document(userId).set(user).await()
            userId
        }catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}