package com.example.mess

import android.provider.SyncStateContract
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException;
import okhttp3.MediaType.Companion.toMediaType


class Repository : Callback {

    val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    val URL = "http://10.0.2.2:1337/mess"
    var newInfo: Info = MainActivity.actualInfo
    private var onRequestCompleteListener: OnRequestCompleteListener? = null

    fun sync(info: Info, callback: OnRequestCompleteListener) {
        this.onRequestCompleteListener = callback
        val body: RequestBody = RequestBody.create(JSON, infoToJson(info))
        val request = Request.Builder()
            .url(URL)
            .post(body)
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(this)
    }

    fun sendKey(key: String) {
        val body: RequestBody = RequestBody.create("text/plain; charset=utf-8".toMediaType(), key)
        val request = Request.Builder()
            .url(URL)
            .put(body)
            .build()
        val client = OkHttpClient()
        GlobalScope.launch { client.newCall(request).execute() }
    }

    fun upload(info: Info, callback: OnRequestCompleteListener) {
        this.onRequestCompleteListener = callback
        val body: RequestBody = RequestBody.create(JSON, infoToJson(info))
        val request = Request.Builder()
            .url(URL)
            .post(RequestBody.create(JSON, ""))
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(this)
    }

    override fun onFailure(call: Call, e: IOException) {
        onRequestCompleteListener?.onError()
    }

    override fun onResponse(call: Call, response: Response) {
        if (response.isSuccessful) {
            val body = response.body?.string()
            newInfo = if (body != null) jsonToInfo(body) else MainActivity.actualInfo
        }
        onRequestCompleteListener?.onSuccess(newInfo)
    }

    private fun infoToJson(info: Info): String {
        val jsonString = Gson().toJson(info)
        return jsonString
    }

    private fun jsonToInfo(jsonString: String): Info {
        val info: Info = Gson().fromJson(jsonString, Info::class.java)
        return info
    }

}

interface OnRequestCompleteListener {
    fun onSuccess(info: Info)
    fun onError()
}