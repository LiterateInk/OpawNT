package ink.literate.opawnt.decoders

import ink.literate.opawnt.models.UserRequirements
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

fun decodeUserRequirements(userReqs: JsonObject) = UserRequirements(
    forceChangePassword = userReqs["forceChangePassword"]!!.jsonPrimitive.boolean,
    needRevalidateTerms = userReqs["needRevalidateTerms"]!!.jsonPrimitive.boolean,
    needRevalidateEmail = userReqs["needRevalidateEmail"]!!.jsonPrimitive.boolean,
    needRevalidateMobile = userReqs["needRevalidateMobile"]!!.jsonPrimitive.boolean,
    needMfa = userReqs["needMfa"]!!.jsonPrimitive.boolean
)