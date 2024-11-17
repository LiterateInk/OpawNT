package ink.literate.opawnt.models

import kotlinx.datetime.LocalDateTime

data class BoxSender(
    val id: String,
    val name: String
)

data class BoxConversation(
    val id: String,
    val subject: String,
    val from: BoxSender,
    val to: List<String>,
    val cc: List<String>,
    val cci: List<String>,
    val date: LocalDateTime,
    val unread: Boolean,
    val response: Boolean,
    val hasAttachment: Boolean
)