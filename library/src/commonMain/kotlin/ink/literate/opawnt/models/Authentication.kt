package ink.literate.opawnt.models

import io.ktor.client.*
import kotlinx.datetime.Instant

data class Authentication(
    val clientId: String,
    val clientSecret: String,
    val instanceUrl: String,
    val tokenType: String,
    var accessToken: String,
    val refreshToken: String,
    val expiration: Instant,
    val scope: List<String>,
    val client: HttpClient
)