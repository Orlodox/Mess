package com.example.mess

import java.util.*

data class Message(
    val time: Date,
    val content: String,
    val from: Int,
    val to: Int
)