package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.decoders.decodeBoxConversation
import ink.literate.opawnt.models.Authentication
import ink.literate.opawnt.models.BoxConversation
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

suspend fun conversations(auth: Authentication, box: ConversationBox, page: Int = 0, unread: Boolean = false): List<BoxConversation> {
    val request = Request("/conversation/list/${box.name}?page=$page&unread=$unread")
    request.useAuthentication(auth)

    val response = request.send(auth.client).jsonArray

    return response.map { decodeBoxConversation(it.jsonObject) }
}