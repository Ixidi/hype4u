package xyz.ixidi.hype4u.core.framework.permission.group

import org.bukkit.entity.Player
import java.util.*

interface Group {

    val name: String
    var isRoot: Boolean

    fun isMember(uuid: UUID): Boolean
    fun isMember(player: Player) = isMember(player.uniqueId)

    fun getAllMembers(): List<UUID>

    fun addMember(uuid: UUID)
    fun addMember(player: Player) {
        addMember(player.uniqueId)
    }

    fun removeMember(uuid: UUID)
    fun removeMember(player: Player) {
        removeMember(player.uniqueId)
    }

    fun getAllPermissions(): List<String>
    fun addPermission(permission: String)
    fun removePermission(permission: String)
    fun hasPermission(permission: String): Boolean

}