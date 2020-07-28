package com.linxo.test.api

import android.util.Log
import com.linxo.test.app.Constants
import com.linxo.test.app.DataManager
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.*

class Webservice {

    @Throws(IOException::class)
    fun sync(): String {

        val url = Constants.WEBSERVICE_URL
        val client = OkHttpClient().newBuilder()
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        return try {

            val response = client.newCall(request).execute()
            val body = response.body
            val sResponse = Objects.requireNonNull(body!!).string().trim { it <= ' ' }
            body.close()

            if (response.isSuccessful) {
                DataManager.instance!!.onSyncResponse(sResponse)
                Constants.WEBSERVICE_SYNC_OK
            } else {
                Constants.WEBSERVICE_SYNC_ERROR
            }
        } catch (e: Exception) {
            Log.e("SYNCHRONISATION", e.message!!)
            Constants.WEBSERVICE_SYNC_ERROR
        }
    }

    companion object {

        const val SYNC_ID = "result"

        val instance: Webservice? = null
            get() = field ?: Webservice()
    }
}