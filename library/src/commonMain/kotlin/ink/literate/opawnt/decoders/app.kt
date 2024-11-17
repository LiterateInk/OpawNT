package ink.literate.opawnt.decoders

import ink.literate.opawnt.models.App
import kotlinx.serialization.json.*

fun decodeApp (app: JsonObject) = App(
    name = app["name"]!!.jsonPrimitive.content,
    address = app["address"]!!.jsonPrimitive.content,
    iconURL = app["icon"]!!.jsonPrimitive.content,
    target = app["target"]!!.jsonPrimitive.contentOrNull,
    displayName = app["displayName"]!!.jsonPrimitive.content,
    display = app["display"]!!.jsonPrimitive.boolean,
    prefix = app["prefix"]!!.jsonPrimitive.content,
    casType = app["casType"]!!.jsonPrimitive.contentOrNull,
    scope = app["scope"]!!.jsonArray.map { it.jsonPrimitive.content },
    isExternal = app["isExternal"]!!.jsonPrimitive.boolean
)