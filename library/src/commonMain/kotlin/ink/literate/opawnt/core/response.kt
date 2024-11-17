package ink.literate.opawnt.core

import io.ktor.client.statement.*
import kotlinx.serialization.json.*

suspend fun handleResponse(response: HttpResponse): JsonElement {
    val responseBody = response.bodyAsText()

    val content = Json.parseToJsonElement(responseBody)

    if (response.status.value < 200 || response.status.value > 299) {
        val contentObj = content.jsonObject
        throw Error("${contentObj["error"]!!.jsonPrimitive.content} (${response.status.value})")
    }

    return content
}