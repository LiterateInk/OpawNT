package ink.literate.opawnt.decoders

import ink.literate.opawnt.models.Attachment
import ink.literate.opawnt.models.Authentication
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

fun decodeAttachment(auth: Authentication, messageId: String, attachment: JsonObject) = Attachment(
    id = attachment["id"]!!.jsonPrimitive.content,
    name = attachment["name"]!!.jsonPrimitive.content,
    url = auth.instanceUrl + "conversation/message/$messageId/attachment/${attachment["id"]!!.jsonPrimitive.content}",
    charset = attachment["charset"]!!.jsonPrimitive.content,
    filename = attachment["filename"]!!.jsonPrimitive.content,
    contentType = attachment["contentType"]!!.jsonPrimitive.content,
    contentTransferEncoding = attachment["contentTransferEncoding"]!!.jsonPrimitive.content,
    size = attachment["size"]!!.jsonPrimitive.long,
)