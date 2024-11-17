package ink.literate.opawnt.models

data class UserRequirements(
    val forceChangePassword: Boolean,
    val needRevalidateTerms: Boolean,
    val needRevalidateEmail: Boolean,
    val needRevalidateMobile: Boolean,
    val needMfa: Boolean
)