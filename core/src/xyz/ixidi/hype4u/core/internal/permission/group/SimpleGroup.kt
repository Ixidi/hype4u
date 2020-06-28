package xyz.ixidi.hype4u.core.internal.permission.group

import org.bukkit.plugin.Plugin
import xyz.ixidi.hype4u.core.framework.permission.group.Group
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager
import java.util.*
import kotlin.collections.ArrayList

internal class SimpleGroup(
    override val name: String,
    private val groupManager: GroupManager,
    private val plugin: Plugin
) : Group {

    class CannotRemoveFromPrimaryGroup : Exception()

    override var isRoot: Boolean = false

    private val permissionList = ArrayList<String>()
    private val memberList = ArrayList<UUID>()

    override fun isMember(uuid: UUID): Boolean = memberList.contains(uuid)

    override fun getAllMembers(): List<UUID> = memberList.toList()

    override fun addMember(uuid: UUID) {
        if (memberList.contains(uuid)) return

        memberList.add(uuid)
        if (groupManager.getPrimaryGroupByUUID(uuid) == null) {
            groupManager.setAsPrimaryGroup(uuid, this)
        }
    }

    override fun removeMember(uuid: UUID) {
        memberList.remove(uuid)
    }

    override fun getAllPermissions(): List<String> {
        return permissionList.toList()
    }

    override fun addPermission(permission: String) {
        permissionList.add(permission)
    }

    override fun removePermission(permission: String) {
        permissionList.remove(permission)
    }

    override fun hasPermission(permission: String): Boolean {
        return permissionList.contains(permission)
    }

}