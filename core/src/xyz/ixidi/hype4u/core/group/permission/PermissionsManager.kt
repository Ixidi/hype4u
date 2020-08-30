package xyz.ixidi.hype4u.core.group.permission

import org.bukkit.entity.Player

interface PermissionsManager {

    fun givePermissions(player: Player)
    fun removePermissions(player: Player)

}