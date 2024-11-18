package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.decoders.decodeMessage
import ink.literate.opawnt.models.Authentication
import ink.literate.opawnt.models.Message
import kotlinx.serialization.json.jsonObject

/**
 * Fetch a message
 *
 * @param auth Authentication
 * @param id Message id
 */
suspend fun message(auth: Authentication, id: String): Message {
    val request = Request("/conversation/message/$id")
    request.useAuthentication(auth)

    val response = request.send(auth.client)!!.jsonObject

    return decodeMessage(auth, response)
}