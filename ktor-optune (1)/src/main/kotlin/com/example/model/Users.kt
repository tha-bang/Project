package com.example.model

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val userId = varchar("userId", 128)
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val signUpMethod = varchar("signUpMethod", 255)
    val skills = text("skills")
    val interests = text("interests")
    val education = varchar("education", 255)
    val cv = text("cv")

    override val primaryKey = PrimaryKey(userId)
}