package ink.literate.opawnt.core

import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

suspend fun handleResponse(response: HttpResponse): JsonObject {
    val responseBody = response.bodyAsText()

    val content = Json.parseToJsonElement(responseBody).jsonObject

    if (content["error"] != null)
        throw Exception(content["error"]!!.jsonPrimitive.content)

    return content
}