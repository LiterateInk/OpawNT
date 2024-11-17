package ink.literate.opawnt.decoders

import ink.literate.opawnt.models.Authentication
import ink.literate.opawnt.models.BoxSender
import ink.literate.opawnt.models.Message
import kotlinx.serialization.json.*

fun decodeMessage (auth: Authentication, message: JsonObject): Message {
    val from = BoxSender(id = message["from"]!!.jsonPrimitive.content, name = message["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == message["from"]!!.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content)
    val to = message["to"]!!.jsonArray.map {toId -> message["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == toId.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content }
    val cc = message["cc"]!!.jsonArray.map {toId -> message["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == toId.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content }
    val cci = message["cci"]!!.jsonArray.map {toId -> message["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == toId.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content }

    return Message(
        id = message["id"]!!.jsonPrimitive.content,
        parentId = message["parent_id"]!!.jsonPrimitive.contentOrNull,
        subject = message["subject"]!!.jsonPrimitive.content,
        body = message["body"]!!.jsonPrimitive.content,
        from = from,
        to = to,
        cc = cc,
        cci = cci,
        threadId = message["thread_id"]!!.jsonPrimitive.contentOrNull,
        attachments = message["attachments"]!!.jsonArray.map { decodeAttachment(auth, message["id"]!!.jsonPrimitive.content, it.jsonObject) }
    )
}