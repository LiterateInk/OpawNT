package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.models.Authentication
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.DurationUnit
import kotlin.time.toDuration

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

    val response = request.send(client)

    return Authentication(
        clientId = clientId,
        clientSecret = clientSecret,
        instanceUrl = instanceUrl,
        tokenType = response["token_type"]!!.jsonPrimitive.content,
        accessToken = response["access_token"]!!.jsonPrimitive.content,
        refreshToken = response["refresh_token"]!!.jsonPrimitive.content,
        expiration = Clock.System.now().plus(response["expires_in"]!!.jsonPrimitive.int.toDuration(DurationUnit.SECONDS)),
        scope = response["scope"]!!.jsonPrimitive.content.split(' '),
        client = client
    )
}