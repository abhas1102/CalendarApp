package com.example.calendarapp.network

import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body

        val url = request.url
        val method = request.method

        val requestBodyString = bodyToString(requestBody)

        println("Sending $method request to $url with body: $requestBodyString")

        return chain.proceed(request)
    }

    private fun bodyToString(requestBody: RequestBody?): String {
        try {
            val buffer = Buffer()
            requestBody?.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "Error reading request body"
        }
    }
}