package com.example.mess

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_second.*
import java.util.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment() : Fragment(), OnRequestCompleteListener, CoroutineScope {

    var chatPosition = MainActivity.chosenChat
    lateinit var chatAdapter: ChatAdapter
    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        job = Job()
        CoroutineScope(coroutineContext).launch {
            while (true) {
                withContext(Dispatchers.Main) {
                    Repository().upload(
                        MainActivity.actualInfo,
                        this@SecondFragment
                    )
                }
                delay(1000L)
            }
        }
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (context as MainActivity).fabVisibility(false)
        update()
//        initSocket()
        messageInput.setOnKeyListener(
            object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER
                    ) {
                        MainActivity.actualInfo.user.chats[chatPosition].addMessage(
                            Message(
                                Date(),
                                messageInput.text.toString(),
                                MainActivity.actualInfo.user.userID,
                                MainActivity.actualInfo.user.chats[MainActivity.chosenChat].with
                            )
                        )
                        sync()
                        messageInput.text = null
                        messageInput.clearFocus()
                        hideKeyboard()
                        messagesRecycler.scrollToPosition(chatAdapter.itemCount - 1)
                        chatAdapter.notifyDataSetChanged()
                        return true
                    }
                    return false
                }
            })
    }

    private fun update() {
        chatAdapter = ChatAdapter(
            this.context,
            if (chatPosition >= 0) MainActivity.actualInfo.user.chats[chatPosition].messages else arrayListOf()
        )
        messagesRecycler.apply {
            layoutManager = LinearLayoutManager(MainActivity.context)
            adapter = chatAdapter
        }
        chatAdapter.notifyDataSetChanged()
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = requireActivity().currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun initSocket() {
        GlobalScope.launch {
            while (true) {
                withContext(Dispatchers.Main) {
                    Repository().upload(
                        MainActivity.actualInfo,
                        this@SecondFragment
                    )
                }
                delay(1000L)
            }
        }
    }

    private fun sync() {
        GlobalScope.launch {
            Repository().sync(
                MainActivity.actualInfo,
                this@SecondFragment
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onSuccess(newInfo: Info) {
        MainActivity.actualInfo = newInfo
        MainScope().launch {
            update()
        }
    }

    override fun onError() {

    }
}
