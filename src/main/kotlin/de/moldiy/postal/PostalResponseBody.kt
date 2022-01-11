package de.moldiy.postal

import com.google.gson.annotations.SerializedName

class PostalResponseBody {

    enum class PostalResponseStatus {
        @SerializedName("success")
        SUCCESS,
        @SerializedName("error")
        ERROR,
        @SerializedName("invalid-json")
        INVALID_JSON
    }

    @SerializedName("status")
    lateinit var status: PostalResponseStatus

    @SerializedName("time")
    var time: Double = 0.0

    @SerializedName("flags")
    var flags: Any? = null

    @SerializedName("data")
    var data: Data? = null

    @SerializedName("details")
    var details: String = ""

    class Data {

        enum class Code {
            @SerializedName("NoContent")
            NO_CONTENT,
            @SerializedName("FromAddressMissing")
            FORM_ADDRESS_MISSING
        }

        @SerializedName("message_id")
        var message_id: String? = null

        @SerializedName("code")
        var code: Code? = null

        @SerializedName("message")
        var message: String? = null

    }
}