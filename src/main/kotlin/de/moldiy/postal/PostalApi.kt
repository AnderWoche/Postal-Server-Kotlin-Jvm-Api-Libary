package de.moldiy.postal

import com.google.gson.Gson
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.ConnectException
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class PostalApi(val ip: String, val key: String) {

    private val gson = Gson()

    var POSTAL_SERVER_API_KEY = "X-Server-API-Key"
    var POSTAL_REQUEST_METHOD = "POST"

    val sendURL = URL("$ip/api/v1/send/message")

    val executor = Executors.newCachedThreadPool()

    fun sendEmail(builder: PostalEmailBuilder): PostalResponseBody {
        val connection = sendURL.openConnection() as HttpsURLConnection
        connection.setRequestProperty(POSTAL_SERVER_API_KEY, key)
        connection.doInput = true
        connection.doOutput = true
        connection.requestMethod = POSTAL_REQUEST_METHOD

        connection.setRequestProperty("Content-Type", "application/json")

//        try {
            val outputStream = connection.outputStream

            val writer = OutputStreamWriter(outputStream, "UTF-8")
            writer.write(builder.toJson())
            writer.flush()
            writer.close()
            outputStream.close()
//        } catch(e: ConnectException) {
//
//            val response = PostalResponseBody()
//            response.status = PostalResponseBody.PostalResponseStatus.ERROR
//            response.
//
//        }

        connection.connect()


        val reader = InputStreamReader(connection.inputStream)
        val responseString = reader.readText()


        return this.gson.fromJson(responseString, PostalResponseBody::class.java)
    }

    fun sendEmail(builder: PostalEmailBuilder, responseFunction: (response: PostalResponseBody) -> Unit) {
        this.executor.execute {
            responseFunction(this.sendEmail(builder))
        }
    }

    fun close() {
        this.executor.shutdown()
    }

}