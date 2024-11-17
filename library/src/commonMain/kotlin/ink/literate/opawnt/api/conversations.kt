package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.decoders.decodeBoxConversation
import ink.literate.opawnt.models.Authentication
import ink.literate.opawnt.models.BoxConversation
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

/**
 * Get the list of conversations from a conversation box.
 *
 * @param auth Authentication
 * @param box Conversation box (either INBOX, DRAFT or OUTBOX)
 * @param unread Only fetch unread messages if set to true (defaults to `false`)
 */
suspend fun conversations(auth: Authentication, box: ConversationBox, page: Int = 0, unread: Boolean = false): List<BoxConversation> {
    val request = Request("/conversation/list/${box.name}?page=$page&unread=$unread")
    request.useAuthentication(auth)

    val response = request.send(auth.client).jsonArray

    return response.map { decodeBoxConversation(it.jsonObject) }
}