package com.example


import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table

object DatabaseFactory {
    fun init() {
        val config = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://localhost:5432/postgres" // <-- IMPORTANT: Change this
            username = "postgres" // <-- IMPORTANT: Change this
            password = "Optune" // <-- IMPORTANT: Change this
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)

        // This is where you would define your tables
        // For example, if you have a Users table:
        // transaction {
        //     SchemaUtils.create(Users)
        // }
    }
}

// Example of a table definition
// object Users : Table() {
//     val id = integer("id").autoIncrement()
//     val name = varchar("name", 255)
//     override val primaryKey = PrimaryKey(id)
// }