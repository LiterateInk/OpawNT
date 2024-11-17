package ink.literate.opawnt.models

data class Message(
    val id: String,
    val parentId: String?,
    val subject: String,
    val body: String,
    val from: BoxSender,
    val to: List<String>,
    val cc: List<String>,
    val cci: List<String>,
    val threadId: String?,
    val attachments: List<Attachment>
)