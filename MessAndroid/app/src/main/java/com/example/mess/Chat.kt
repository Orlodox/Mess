package com.example.mess

data class Chat(
    val with: Int,
    val messages: ArrayList<Message>
) {
    fun addMessage(m: Message) {
        messages.add(m)
    }
}