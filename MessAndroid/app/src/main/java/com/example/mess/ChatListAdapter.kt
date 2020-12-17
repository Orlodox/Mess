package com.example.mess

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


internal class ChatListAdapter(context: Context?, private val chats: ArrayList<Chat>) :
    RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chats[position]
        holder.chatTitle.text =
            MainActivity.initInfo.user.friends.find { it.userID == chat.with }?.name
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val chatTitle = view.findViewById(R.id.chatTitle) as TextView
    }
}