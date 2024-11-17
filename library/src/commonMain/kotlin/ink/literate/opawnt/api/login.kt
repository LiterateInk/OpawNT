package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.models.Authentication
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Log in
 *
 * @param clientId Client id
 * @param clientSecret Client secret
 * @param grantType Grant type (`saml2` or `refresh_token`)
 * @param params Additional login parameters
 * @param instanceUrl OpenENT instance url
 * @param httpClient An optional http client
 */
suspend fun login(clientId: String, clientSecret: String, grantType: String, params: Map<String, String>, instanceUrl: String, httpClient: HttpClient = HttpClient()): Authentication {
    val client = httpClient.config {
        defaultRequest {
            url(instanceUrl)
        }
    }

    val request = Request("/auth/oauth2/token")
    request.setHttpMethod(HttpMethod.Post)
    request.setBody(
        mapOf(
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "grant_type" to grantType
        ) + params
    )

    val response = request.send(client).jsonObject
    val finalInstanceUrl = when (instanceUrl.endsWith("/")) {true -> instanceUrl false -> instanceUrl + "/"}

    return Authentication(
        clientId = clientId,
        clientSecret = clientSecret,
        instanceUrl = finalInstanceUrl,
        tokenType = response["token_type"]!!.jsonPrimitive.content,
        accessToken = response["access_token"]!!.jsonPrimitive.content,
        refreshToken = response["refresh_token"]!!.jsonPrimitive.content,
        expiration = Clock.System.now().plus(response["expires_in"]!!.jsonPrimitive.int.toDuration(DurationUnit.SECONDS)),
        scope = response["scope"]!!.jsonPrimitive.content.split(' '),
        client = client
    )
}