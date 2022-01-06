package de.moldiy.postal

const val MAX_EMAIL_ADDRESSES = 50

open class PostalEmailBuilder {

    internal val to = ArrayList<String>()
    internal val cc = ArrayList<String>()
    internal val bcc = ArrayList<String>()

    var from: String? = null
    var sender: String? = null
    var subject: String? = null
    var tag: String? = null
    var reply_to: String? = null
    var plain_body: String? = null
    var html_body: String? = null

    @Deprecated("Not Implemented yet")
    val attachments = ArrayList<Any>()

    // headers

    var bounce = false

    fun addRecipients(email: String) {
        if (this.to.size >= MAX_EMAIL_ADDRESSES) {
            throw MaxEmailAddressesReachedException("You can't habe more than ")
        }
        this.to.add(email)
    }

    fun addRecipientsToCC(email: String) {
        if (this.cc.size >= MAX_EMAIL_ADDRESSES) {
            throw MaxEmailAddressesReachedException("You can't habe more than ")
        }
        this.cc.add(email)
    }

    fun addRecipientsToBCC(email: String) {
        if (this.bcc.size >= MAX_EMAIL_ADDRESSES) {
            throw MaxEmailAddressesReachedException("You can't habe more than ")
        }
        this.bcc.add(email)
    }

    fun toJson(): String {
        val builder = StringBuilder()
        builder.append("{")
        if (this.to.isNotEmpty()) {
            builder.append("\"to\":")
            builder.append("[")
            for(i in this.to.indices) {
                val email = this.to[i]
                if(i != 0) {
                    builder.append(",")
                }
                builder.append("\"$email\"")
            }
            builder.append("]")
        }
        if(this.cc.isNotEmpty()) {
            builder.append(",")
            builder.append("\"cc\":")
            builder.append("[")
            for(i in this.cc.indices) {
                val email = this.cc[i]
                if(i != 0) {
                    builder.append(",")
                }
                builder.append("\"$email\"")
            }
            builder.append("]")
        }
        if(this.bcc.isNotEmpty()) {
            builder.append(",")
            builder.append("\"bcc\":")
            builder.append("[")
            for(i in this.bcc.indices) {
                val email = this.bcc[i]
                if(i != 0) {
                    builder.append(",")
                }
                builder.append("\"$email\"")
            }
            builder.append("]")
        }
        this.from?.let { builder.append(",").append("\"from\":").append("\"$it\"") }
        this.sender?.let { builder.append(",").append("\"sender\":").append("\"$it\"") }
        this.subject?.let { builder.append(",").append("\"subject\":").append("\"$it\"") }
        this.tag?.let { builder.append(",").append("\"tag\":").append("\"$it\"") }
        this.reply_to?.let { builder.append(",").append("\"reply_to\":").append("\"$it\"") }
        this.plain_body?.let { builder.append(",").append("\"plain_body\":").append("\"$it\"") }
        this.html_body?.let { builder.append(",").append("\"html_body\":").append("\"$it\"") }
        //attachments
        // headers
        if(this.bounce) {
           builder.append(",").append("\"bounce\":").append("\"$bounce\"")
        }
        builder.append("}")
        return builder.toString()
    }

    class MaxEmailAddressesReachedException(message: String = "") : RuntimeException(message)
}