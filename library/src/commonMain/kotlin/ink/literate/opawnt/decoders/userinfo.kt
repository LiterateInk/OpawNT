package ink.literate.opawnt.decoders

import ink.literate.opawnt.models.UserInfo
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.*

fun decodeUserInfo(userInfo: JsonObject) = UserInfo(
    level = userInfo["level"]!!.jsonPrimitive.content,
    email = userInfo["email"]!!.jsonPrimitive.content,
    mobile = userInfo["mobile"]!!.jsonPrimitive.content,
    login = userInfo["login"]!!.jsonPrimitive.content,
    lastName = userInfo["lastName"]!!.jsonPrimitive.content,
    firstName = userInfo["firstName"]!!.jsonPrimitive.content,
    birthDate = LocalDate.parse(userInfo["birthDate"]!!.jsonPrimitive.content),
    username = userInfo["username"]!!.jsonPrimitive.content,
    type = userInfo["type"]!!.jsonPrimitive.content,
    hasPw = userInfo["hasPw"]!!.jsonPrimitive.boolean,
    optionEnabled = userInfo["optionEnabled"]!!.jsonArray,
    userId = userInfo["userId"]!!.jsonPrimitive.content,
    uai = userInfo["uai"]!!.jsonArray.map { it.jsonPrimitive.content },
    hasApp = userInfo["hasApp"]!!.jsonPrimitive.boolean,
    ignoreMFA = userInfo["ignoreMFA"]!!.jsonPrimitive.boolean,
    schoolName = userInfo["schoolName"]!!.jsonPrimitive.content,
    className = userInfo["classId"]!!.jsonPrimitive.content.split('$')[1]
)