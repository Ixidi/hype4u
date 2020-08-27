package xyz.ixidi.hype4u.spigot.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import xyz.ixidi.hype4u.spigot.core.group.GroupFormatter
import xyz.ixidi.hype4u.spigot.core.group.permission.PermissionsManager
import xyz.ixidi.hype4u.spigot.core.repository.user.UserRepository
import xyz.ixidi.hype4u.spigot.core.user.UserManager

class PlayerJoinListener(
    private val userManager: UserManager,
    private val userRepository: UserRepository,
    private val permissionsManager: PermissionsManager
) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val user = userRepository.loadUser(event.player.uniqueId)
        userManager.addOnlineUser(user)

        event.player.setDisplayName(GroupFormatter.formatDisplayName(event.player, user))
        permissionsManager.givePermissions(event.player)
    }

}