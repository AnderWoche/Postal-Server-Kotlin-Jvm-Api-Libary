package de.moldiy.postal

import com.google.gson.Gson

class EmailSendTest {



}

fun main() {


    val postal = PostalApi("https://toiletpapertycoon.com:444", "ouegiS9f9j5fobQPSMPmZqs9")

    val builder = PostalEmailBuilder()
    builder.addRecipients("humanndavid@gmail.com")
    builder.from = "Email-Verification<no-reply@toiletpapertycoon.com>"
    builder.subject = "Toilet Paper Tycoon Account Email-Verification."
    builder.plain_body = "Please click on this link to confirm your email."

    val response = postal.sendEmail(builder)


//    val response = Gson().fromJson("{ \"status\":\"error\", \"time\":\"0.02\"}", PostalResponseBody::class.java)

    println(response.status)
    println(response.time)
    println(response.data?.message_id)
    println(response.data?.code)
    println(response.data?.message)
    println(response.details)

}