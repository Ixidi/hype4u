package xyz.ixidi.hype4u.core.feature.group.permission

import org.bukkit.entity.Player

interface PermissionsManager {

    fun givePermissions(player: Player)
    fun removePermissions(player: Player)

}