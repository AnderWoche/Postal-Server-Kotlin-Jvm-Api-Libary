package de.moldiy.postal

import com.google.gson.annotations.SerializedName

class PostalResponseBody {

    enum class PostalResponseStatus {
        @SerializedName("success")
        SUCCESS,
        @SerializedName("error")
        ERROR
    }

    @SerializedName("status")
    lateinit var status: PostalResponseStatus

    @SerializedName("time")
    var time: Double = 0.0

    @SerializedName("flags")
    var flags: Array<String> = emptyArray()

    @SerializedName("data")
    var data: Array<Any> = emptyArray()
}