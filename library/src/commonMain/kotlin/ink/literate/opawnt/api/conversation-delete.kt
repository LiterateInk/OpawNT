package ink.literate.opawnt.api

import ink.literate.opawnt.core.Request
import ink.literate.opawnt.models.Authentication
import io.ktor.http.*
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

suspend fun conversationDelete(auth: Authentication, ids: List<String>) {
    val request = Request("/conversation/delete")
    request.useAuthentication(auth)
    request.setJSON(
        buildJsonObject {
            put("id", buildJsonArray {
                ids.forEach { add(it) }
            })
        }
    )
    request.setHttpMethod(HttpMethod.Put)

    request.send(auth.client)
}

suspend fun conversationDelete(auth: Authentication, id: String) {
    val request = Request("/conversation/delete")
    request.useAuthentication(auth)
    request.setJSON(
        buildJsonObject {
            put("id", buildJsonArray {
                add(id)
            })
        }
    )
    request.setHttpMethod(HttpMethod.Put)

    request.send(auth.client)
}