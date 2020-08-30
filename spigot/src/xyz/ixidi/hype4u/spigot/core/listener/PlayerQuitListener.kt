package xyz.ixidi.hype4u.spigot.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import xyz.ixidi.hype4u.spigot.core.group.permission.PermissionsManager
import xyz.ixidi.hype4u.spigot.core.repository.userinfo.UserRepository
import xyz.ixidi.hype4u.spigot.core.user.UserManager

class PlayerQuitListener(
    private val userManager: UserManager,
    private val userRepository: UserRepository,
    private val permissionsManager: PermissionsManager
) : Listener {

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val user = userManager.getOnlineUser(event.player.uniqueId) ?: return
        userRepository.saveUser(user)
        permissionsManager.removePermissions(event.player)
    }

}