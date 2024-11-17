package ink.literate.opawnt.core

import ink.literate.opawnt.api.login
import ink.literate.opawnt.models.Authentication
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

class Request(path: String) {
    var path: String = ""
    var headers: MutableMap<String, String> = mutableMapOf("User-Agent" to "@literateink/turbawself")
    var content = ""
    var method: HttpMethod = HttpMethod.Get

    init {
        this.path = path
    }

    fun setBody(params: Map<String, String>) {
        params.forEach { (t, u) -> this.content += "$t=$u&" }
        this.headers["Content-Type"] = "application/x-www-form-urlencoded"
    }

    fun setJSON(json: JsonElement): Request {
        this.method = HttpMethod.Post
        this.content = Json.encodeToString(json)
        this.headers["Content-Type"] = "application/json"

        return this
    }

    fun setHttpMethod(httpMethod: HttpMethod) {
        this.method = httpMethod
    }

    suspend fun useAuthentication(auth: Authentication) {
        if (auth.expiration < Clock.System.now()) {
            val tempAuth = login(auth.clientId, auth.clientSecret, "refresh_token", mapOf("refresh_token" to auth.refreshToken), auth.instanceUrl)
            auth.accessToken = tempAuth.accessToken
        }

        this.headers["Authorization"] = "Bearer ${auth.accessToken}"
    }

    suspend fun send(client: HttpClient): JsonElement {
        val httpMethod = this.method
        val httpHeaders = this.headers
        val httpContent = this.content

        val response =
            client.request (this.path) {
                method = httpMethod
                headers { httpHeaders.forEach { (k, v) -> header(k, v) } }
                setBody(httpContent)
            }

        return handleResponse(response)
    }
}