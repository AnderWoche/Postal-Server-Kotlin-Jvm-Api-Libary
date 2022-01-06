package de.moldiy.postal

import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class PostalApi(val ip: String, val key: String) {

    var POSTAL_SERVER_API_KEY = "X-Server-API-Key"
    var POSTAL_REQUEST_METHOD = "POST"

    val sendURL = URL("$ip/api/v1/send/message")

    val executor = Executors.newCachedThreadPool()

    fun sendEmail(builder: PostalEmailBuilder): String {
        val connection = sendURL.openConnection() as HttpsURLConnection
        connection.setRequestProperty(POSTAL_SERVER_API_KEY, key)
        connection.doInput = true
        connection.doOutput = true
        connection.requestMethod = POSTAL_REQUEST_METHOD

        connection.setRequestProperty("Content-Type", "application/json")

        val outputStream = connection.outputStream

        val writer = OutputStreamWriter(outputStream, "UTF-8")
        writer.write(builder.toJson())
        writer.flush()
        writer.close()
        outputStream.close()

        connection.connect()


        val reader = InputStreamReader(connection.inputStream)

        return reader.readText()
    }

    fun sendEmail(builder: PostalEmailBuilder, responseFunction: (response: String) -> Unit) {
        this.executor.execute {
            responseFunction(this.sendEmail(builder))
        }
    }

    fun close() {
        this.executor.shutdown()
    }

}