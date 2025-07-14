package com.example.database

import com.example.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import io.ktor.server.application.Application
import io.ktor.server.application.log
import java.sql.Connection
import java.sql.DriverManager

object DatabaseFactory {
    fun init(app: Application) {
        val url = app.environment.config.property("postgres.url").getString()
        val user = app.environment.config.property("postgres.user").getString()
        val password = app.environment.config.property("postgres.password").getString()

        val database = Database.connect(url, user = user, password = password)
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    fun Application.connectToPostgres(embedded: Boolean): Connection {
        Class.forName("org.postgresql.Driver")
        if (embedded) {
            log.info("Using embedded H2 database for testing; replace this flag to use postgres")
            return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "root", "")
        } else {
            val url = environment.config.property("postgres.url").getString()
            log.info("Connecting to postgres database at $url")
            val user = environment.config.property("postgres.user").getString()
            val password = environment.config.property("postgres.password").getString()

            return DriverManager.getConnection(url, user, password)
        }
    }
}