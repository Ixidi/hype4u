package xyz.ixidi.hype4u.spigot.core.group

class GroupImpl(
    override val name: String,
    override var displayNameFormat: String,
    override var chatFormat: String
) : Group {

    private val permissionMap = HashMap<String, Boolean>()

    override fun getAllPermissions(): Map<String, Boolean> = permissionMap.toMap()

    override fun addPermission(permission: String, allow: Boolean) {
        permissionMap[permission] = allow
    }

    override fun removePermission(permission: String) {
        permissionMap.remove(permission)
    }

    override fun hasPermission(permission: String): Boolean = permissionMap[permission] ?: false

}