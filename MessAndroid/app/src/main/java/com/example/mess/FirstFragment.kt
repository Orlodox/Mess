package com.example.mess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var chats = MainActivity.actualInfo.user.chats

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context as MainActivity).fabVisibility(true)
        chatsRecycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ChatListAdapter(this.context, chats)
        }
        chatsRecycler.addOnItemTouchListener(
            RecyclerItemClickListener(context, chatsRecycler, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    MainActivity.chosenChat = position
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }

                override fun onLongItemClick(view: View?, position: Int) {
                }
            })
        )
//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    fun onChatClickListener(view: View) {

    }
}