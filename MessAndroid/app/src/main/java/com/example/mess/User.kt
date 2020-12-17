package com.example.mess

data class User(
    val userID: Int,
    val name: String,
    val friends: ArrayList<User>,
    val chats: ArrayList<Chat>,
) {
    fun addChats(chats: ArrayList<Chat>) {
        this.chats.addAll(chats)
    }
}