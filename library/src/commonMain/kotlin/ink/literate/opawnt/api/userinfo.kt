package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.decoders.decodeUserInfo
import ink.literate.opawnt.models.Authentication
import ink.literate.opawnt.models.UserInfo
import kotlinx.serialization.json.jsonObject

/**
 * Fetch account information
 *
 * @param auth Authentication
 */
suspend fun userInfo(auth: Authentication): UserInfo {
    val request = Request("/auth/oauth2/userinfo")
    request.useAuthentication(auth)

    val response = request.send(auth.client)!!.jsonObject

    return decodeUserInfo(response)
}