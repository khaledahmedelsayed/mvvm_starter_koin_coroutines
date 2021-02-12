package com.ibtikar.mvvm_starter_koin_coroutines.common.helpers

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.orhanobut.logger.PrettyFormatStrategy
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class ApiPrettyLogger : HttpLoggingInterceptor.Logger {
    @SuppressLint("LogNotTimber")
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val prettyPrintJson = GsonBuilder().setPrettyPrinting()
                    .create().toJson(JsonParser.parseString(message))

                Timber.tag("JsonResponse")
                Timber.i("------ Json Response ------\n$prettyPrintJson")
            } catch (exception: JsonSyntaxException) {
                Timber.e(exception)
            }
        } else {
            Log.d("OKHttp", message)
        }
    }

    companion object {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(5)
            .tag("")
            .build()
    }
}
