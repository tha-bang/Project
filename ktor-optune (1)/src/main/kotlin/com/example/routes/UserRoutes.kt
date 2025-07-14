package com.example.routes

import com.example.database.DatabaseFactory.dbQuery
import com.example.model.User
import com.example.model.Users
import io.ktor.http.* 
import io.ktor.server.application.* 
import io.ktor.server.request.* 
import io.ktor.server.response.* 
import io.ktor.server.routing.* 
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

fun Route.userRoutes() {
    route("/users") {
        get {
            val users = dbQuery { 
                Users.selectAll().map { 
                    User(
                        it[Users.userId],
                        it[Users.name],
                        it[Users.email],
                        it[Users.signUpMethod],
                        it[Users.skills].split(","),
                        it[Users.interests].split(","),
                        it[Users.education],
                        it[Users.cv]
                    )
                }
            }
            call.respond(users)
        }
        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val user = dbQuery {
                Users.select { Users.userId eq id }.map { 
                    User(
                        it[Users.userId],
                        it[Users.name],
                        it[Users.email],
                        it[Users.signUpMethod],
                        it[Users.skills].split(","),
                        it[Users.interests].split(","),
                        it[Users.education],
                        it[Users.cv]
                    )
                }.singleOrNull()
            }
            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "No user with id $id")
            } else {
                call.respond(user)
            }
        }
        post {
            val user = call.receive<User>()
            dbQuery {
                Users.insert {
                    it[userId] = user.userId
                    it[name] = user.name
                    it[email] = user.email
                    it[signUpMethod] = user.signUpMethod
                    it[skills] = user.skills.joinToString(",")
                    it[interests] = user.interests.joinToString(",")
                    it[education] = user.education
                    it[cv] = user.cv
                }
            }
            call.respond(HttpStatusCode.Created)
        }
    }
}