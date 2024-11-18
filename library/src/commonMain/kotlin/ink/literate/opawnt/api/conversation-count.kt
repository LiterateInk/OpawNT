package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.models.Authentication
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

enum class ConversationBox {
    INBOX,
    DRAFT,
    OUTBOX,
    TRASH
}

/**
 * Gets the number of read/unread messages in a conversation box
 *
 * @param auth Authentication authentication
 * @param box Conversation box (either INBOX, DRAFT, OUTBOX or TRASH)
 * @param unread Only fetch unread messages if set to true (defaults to `true`)
 */
suspend fun conversationCount(auth: Authentication, box: ConversationBox, unread: Boolean = true): Int {
    val request = Request("/conversation/count/${box.name}?unread=$unread")
    request.useAuthentication(auth)

    val response = request.send(auth.client)!!.jsonObject

    return response["count"]!!.jsonPrimitive.int
}