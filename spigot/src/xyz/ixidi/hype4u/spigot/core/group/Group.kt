package xyz.ixidi.hype4u.spigot.core.group

interface Group {

    val name: String
    val displayNameFormat: String
    val chatFormat: String

    fun getAllPermissions(): List<String>
    fun addPermission(permission: String)
    fun removePermission(permission: String)
    fun hasPermission(permission: String): Boolean

}