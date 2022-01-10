package de.moldiy.postal

import com.google.gson.Gson

class EmailSendTest {



}

fun main() {

    val response = Gson().fromJson("{ \"status\":\"error\", \"time\":\"0.02\"}", PostalResponseBody::class.java)

    println(response.status)
    println(response.time)

}