package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.decoders.decodeUserRequirements
import ink.literate.opawnt.models.Authentication
import ink.literate.opawnt.models.UserRequirements

suspend fun userRequirements(auth: Authentication): UserRequirements {
    val request = Request("/auth/user/requirements")
    request.useAuthentication(auth)

    val response = request.send(auth.client)

    return decodeUserRequirements(response)
}