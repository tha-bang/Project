package com.example

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val email = varchar("email", 128).uniqueIndex()
    val passwordHash = varchar("password_hash", 256)

    override val primaryKey = PrimaryKey(id)
}