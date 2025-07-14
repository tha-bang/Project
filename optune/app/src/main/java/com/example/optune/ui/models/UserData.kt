package com.example.optune.ui.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class UserData(
    var name: String = "",
    var email: String = "",
    var profileImage: String = "",
    var joinDate: LocalDate = LocalDate.now(),
    var skills: List<String> = emptyList(),
    var interests: List<String> = emptyList(),
    var courses: List<String> = emptyList(),
    var progress: Int = 0,
    var coursesTaken:Int = 0,
){
    fun formattedJoinDate(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return joinDate.format(formatter)
    }
}