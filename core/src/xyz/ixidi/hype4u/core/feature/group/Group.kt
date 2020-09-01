package xyz.ixidi.hype4u.core.feature.group

interface Group {

    val name: String
    var displayNameFormat: String
    var chatFormat: String

    fun getAllPermissions(): Map<String, Boolean>
    fun addPermission(permission: String, allow: Boolean)
    fun removePermission(permission: String)
    fun hasPermission(permission: String): Boolean

}