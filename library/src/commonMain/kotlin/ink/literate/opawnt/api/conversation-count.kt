package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.models.Authentication
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

enum class ConversationBox {
    INBOX,
    DRAFT,
    OUTBOX
}

suspend fun conversationCount(auth: Authentication, box: ConversationBox, unread: Boolean = true): Int {
    val request = Request("/conversation/count/${box.name}?unread=$unread")
    request.useAuthentication(auth)

    val response = request.send(auth.client).jsonObject

    return response["count"]!!.jsonPrimitive.int
}