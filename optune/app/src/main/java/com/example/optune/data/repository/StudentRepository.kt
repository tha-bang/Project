package com.example.optune.data.repository

import com.example.optune.data.model.Student
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun saveStudent(student: Student): String? {
        return try {
            val docRef = firestore.collection("students").add(student).await()
            docRef.id
        } catch (_: Exception) {
            null
        }
    }
}