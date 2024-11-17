package ink.literate.opawnt.models

data class Attachment(
    val id: String,
    val name: String,
    val url: String,
    val charset: String,
    val filename: String,
    val contentType: String,
    val contentTransferEncoding: String,
    val size: Long
)