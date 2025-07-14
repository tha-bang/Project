package com.example.optune.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun listToJson(list: List<String>): String =
    Json.encodeToString(list)

fun jsonToList(json: String): List<String> =
    Json.decodeFromString(json)
