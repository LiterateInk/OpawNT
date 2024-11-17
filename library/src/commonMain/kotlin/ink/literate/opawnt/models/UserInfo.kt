package ink.literate.opawnt.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.JsonElement

data class UserInfo(
    val level: String,
    val email: String,
    val mobile: String,
    val login: String,
    val lastName: String,
    val firstName: String,
    val birthDate: LocalDate,
    val username: String,
    val type: String,
    val hasPw: Boolean,
    val optionEnabled: List<JsonElement>,
    val userId: String,
    val uai: List<String>,
    val hasApp: Boolean,
    val ignoreMFA: Boolean,
    val schoolName: String,
    val className: String
)