package ink.literate.opawnt.decoders

import ink.literate.opawnt.models.Action
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

fun decodeAction (action: JsonObject) = Action(
    name = action["name"]!!.jsonPrimitive.content,
    displayName = action["displayName"]!!.jsonPrimitive.content,
    type = action["type"]!!.jsonPrimitive.content
)