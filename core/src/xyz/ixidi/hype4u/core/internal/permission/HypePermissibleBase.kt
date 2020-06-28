package xyz.ixidi.hype4u.core.internal.permission

import org.bukkit.entity.Player
import org.bukkit.permissions.PermissibleBase
import org.bukkit.permissions.Permission
import xyz.ixidi.hype4u.core.framework.permission.group.GroupManager

class HypePermissibleBase(
    private val player: Player,
    private val groupManager: GroupManager
) : PermissibleBase(player) {

    override fun hasPermission(inName: String): Boolean  {
        println(groupManager.getAllGroupsByPlayer(player).size)
        println("test")
        return groupManager.getAllGroupsByPlayer(player).any { it.isRoot || it.hasPermission(inName) }.also { println("$it $inName") }
    }

    override fun hasPermission(perm: Permission): Boolean = hasPermission(perm.name)

}