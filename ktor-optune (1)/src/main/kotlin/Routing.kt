package com.example

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello from your Ktor Backend!")
        }

        // A simple test route to see if the database connection works
        get("/db-test") {
            try {
                // Perform a simple query
                transaction {
                    // This is just a test query to see if we can connect.
                    // It doesn't assume any tables exist.
                    exec("SELECT 1")
                }
                call.respondText("Database connection successful!")
            } catch (e: Exception) {
                call.respondText("Database connection failed: ${e.message}")
            }
        }
    }
}
