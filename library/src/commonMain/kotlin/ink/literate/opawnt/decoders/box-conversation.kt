package ink.literate.opawnt.decoders

import ink.literate.opawnt.models.BoxConversation
import ink.literate.opawnt.models.BoxSender
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.*

fun decodeBoxConversation(boxConv: JsonObject): BoxConversation {
    val from = BoxSender(id = boxConv["from"]!!.jsonPrimitive.content, name = boxConv["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == boxConv["from"]!!.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content)
    val to = boxConv["to"]!!.jsonArray.map {toId -> boxConv["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == toId.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content }
    val cc = boxConv["cc"]!!.jsonArray.map {toId -> boxConv["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == toId.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content }
    val cci = boxConv["cci"]!!.jsonArray.map {toId -> boxConv["displayNames"]!!.jsonArray.find { it.jsonArray[0].jsonPrimitive.content == toId.jsonPrimitive.content }!!.jsonArray[1].jsonPrimitive.content }

    return BoxConversation(
        id = boxConv["id"]!!.jsonPrimitive.content,
        subject = boxConv["subject"]!!.jsonPrimitive.content,
        from = from,
        to = to,
        cc = cc,
        cci = cci,
        date = Instant.fromEpochMilliseconds(boxConv["date"]!!.jsonPrimitive.long).toLocalDateTime(TimeZone.currentSystemDefault()),
        unread = boxConv["unread"]!!.jsonPrimitive.boolean,
        response = boxConv["response"]!!.jsonPrimitive.boolean,
        hasAttachment = boxConv["hasAttachment"]!!.jsonPrimitive.boolean,
    )
}