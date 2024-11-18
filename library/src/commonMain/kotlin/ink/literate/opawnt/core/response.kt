package ink.literate.opawnt.core

import io.ktor.client.statement.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*

suspend fun handleResponse(response: HttpResponse): JsonElement? {
    val responseBody = response.bodyAsText()
    var content: JsonElement? = null

    try {
        content = Json.parseToJsonElement(responseBody)
    } catch (_: SerializationException) {

    }

    if (response.status.value < 200 || response.status.value > 299) {
        val contentObj = content?.jsonObject

        if (contentObj != null)
            throw Error("${contentObj["error"]!!.jsonPrimitive.content} (${response.status.value})")
        else
            throw Error("An unknown error has occurred (${response.status.value})")
    }

    return content
}