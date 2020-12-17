package com.example.mess

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var firstFragment: FirstFragment
    lateinit var secondFragment: SecondFragment

    companion object {

        var chosenChat: Int = -1
        var key = ""
        val user1 =
            User(1, "Иван", arrayListOf(), arrayListOf())
        val user2 =
            User(2, "Петр", arrayListOf(user1), arrayListOf())
        val user3 =
            User(3, "Андрей", arrayListOf(user1, user2), arrayListOf())
        val me =
            User(0, "Я", arrayListOf(user1, user2, user3), arrayListOf())

        val chat1 = Chat(
            user1.userID, arrayListOf<Message>(
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 2, 8).timeInMillis),
                    "Привет!",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 3, 0).timeInMillis),
                    "Вечер добрый",
                    me.userID,
                    user1.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 3, 52).timeInMillis),
                    "Как дела?",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 4, 5).timeInMillis),
                    "Отлично, как твои?",
                    me.userID,
                    user1.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 5, 18).timeInMillis),
                    "Вполне себе! Чем занят?",
                    user1.userID,
                    me.userID
                )
            )
        )

        val chat2 = Chat(
            user2.userID, arrayListOf<Message>(
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 2, 8).timeInMillis),
                    "Ты не видел мою кошку?",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 3, 0).timeInMillis),
                    "Барсик сбежал??!!",
                    me.userID,
                    user1.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 3, 52).timeInMillis),
                    "Да, со вчерашнего дня найти не можем :((",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 4, 5).timeInMillis),
                    "КОШМАР !!!",
                    me.userID,
                    user1.userID
                )
            )
        )

        val chat3 = Chat(
            user3.userID, arrayListOf<Message>(
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 2, 8).timeInMillis),
                    "Как думаешь, реально пройти собеседование в яндекс?",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 3, 0).timeInMillis),
                    "Люди же как-то проходят – значит, реально. Надо просто постараться.",
                    me.userID,
                    user1.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 3, 52).timeInMillis),
                    "За месяц бы подготовиться и попробовать",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 4, 5).timeInMillis),
                    "А на кого хочешь?",
                    me.userID,
                    user1.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 5, 52).timeInMillis),
                    "На ондроед разработчика",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 6, 5).timeInMillis),
                    "Ого! Слышал, им много платят!",
                    me.userID,
                    user1.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 7, 52).timeInMillis),
                    "Да, даже больше, чем бэкендерам. А ещё и в яндексе...",
                    user1.userID,
                    me.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 6, 5).timeInMillis),
                    "Определённо речь идёт про трёхзначные суммы :)))",
                    me.userID,
                    user1.userID
                ),
                Message(
                    Date(GregorianCalendar(2020, 12, 16, 19, 7, 52).timeInMillis),
                    "Хочется в это верить! Но сперва надо поднатаскаться..",
                    user1.userID,
                    me.userID
                ),
            )
        )


        val initInfo = Info(
            me.apply { addChats(arrayListOf(chat1, chat2, chat3)) },
            "user0"
        )

        var actualInfo = initInfo

        var context: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        fab.setOnClickListener { view ->
            onScanKeyButtonClick()
        }
        context = this
    }

    private fun onScanKeyButtonClick() {
        if (key.isEmpty()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Введите ключ")
            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            builder.setView(input)
            builder.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, which ->
                    run {
                        key = input.text.toString()
                        dialog.cancel()
                        Repository().sendKey(key)
                        fab.backgroundTintList =
                            ContextCompat.getColorStateList(this, R.color.lock)
                        fab.setImageResource(android.R.drawable.ic_secure)
                    }
                })
            builder.setNegativeButton("Отмена",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            val imm: InputMethodManager =
                this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            input.showSoftInputOnFocus = true
            imm.showSoftInput(input, 0)
            builder.show()
        } else {
            key = ""
            fab.setImageResource(android.R.drawable.ic_menu_share)
            fab.backgroundTintList =
                ContextCompat.getColorStateList(this, R.color.unlock)
            Repository().sendKey("")
        }
    }

    fun fabVisibility(isVisible: Boolean) {
        fab.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}