package xyz.ixidi.hype4u.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import xyz.ixidi.hype4u.core.feature.group.GroupFormatter
import xyz.ixidi.hype4u.core.feature.group.permission.PermissionsManager
import xyz.ixidi.hype4u.core.user.UserManager

class PlayerJoinListener(
    private val userManager: UserManager,
    private val permissionsManager: PermissionsManager
) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val user = userManager.getOnlineUser(event.player)

        event.player.run {
            setDisplayName(GroupFormatter.formatDisplayName(event.player, user))
            setPlayerListName(displayName)
        }
        permissionsManager.givePermissions(event.player)
    }

}