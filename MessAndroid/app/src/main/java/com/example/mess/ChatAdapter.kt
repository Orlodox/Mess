package com.example.mess

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView


class ChatAdapter(var context: Context?, private val messages: ArrayList<Message>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(MainActivity.context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.message_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.content
        if (message.from == MainActivity.actualInfo.user.userID)
            (holder.messageText as TextView).updateLayoutParams<RelativeLayout.LayoutParams> {
                addRule(
                    RelativeLayout.ALIGN_PARENT_RIGHT
                )
                holder.messageText.gravity = Gravity.RIGHT
            }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val messageText = view.findViewById(R.id.messageText) as TextView
    }

}