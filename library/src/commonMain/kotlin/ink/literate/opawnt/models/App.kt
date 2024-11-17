package ink.literate.opawnt.models

data class App(
    val name: String,
    val address: String,
    val iconURL: String,
    val target: String?,
    val displayName: String,
    val display: Boolean,
    val prefix: String,
    val casType: String?,
    val scope: List<String>,
    val isExternal: Boolean
)