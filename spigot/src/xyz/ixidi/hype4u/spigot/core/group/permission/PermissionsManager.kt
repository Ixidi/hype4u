package xyz.ixidi.hype4u.spigot.core.group.permission

import org.bukkit.entity.Player

interface PermissionsManager {

    fun givePermissions(player: Player)
    fun removePermissions(player: Player)

}