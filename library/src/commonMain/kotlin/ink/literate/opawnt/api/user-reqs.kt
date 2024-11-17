package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.models.Authentication
import ink.literate.opawnt.models.UserRequirements
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

suspend fun userRequirements(auth: Authentication): UserRequirements {
    val request = Request("/auth/user/requirements")
    request.useAuthentication(auth)

    val response = request.send(auth.client)

    return UserRequirements(
        forceChangePassword = response["forceChangePassword"]!!.jsonPrimitive.boolean,
        needRevalidateTerms = response["needRevalidateTerms"]!!.jsonPrimitive.boolean,
        needRevalidateEmail = response["needRevalidateEmail"]!!.jsonPrimitive.boolean,
        needRevalidateMobile = response["needRevalidateMobile"]!!.jsonPrimitive.boolean,
        needMfa = response["needMfa"]!!.jsonPrimitive.boolean
    )
}