package xyz.ixidi.hype4u.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import xyz.ixidi.hype4u.core.feature.group.permission.PermissionsManager
import xyz.ixidi.hype4u.core.repository.user.UserRepository
import xyz.ixidi.hype4u.core.user.UserManager

class PlayerQuitListener(
    private val userManager: UserManager,
    private val userRepository: UserRepository,
    private val permissionsManager: PermissionsManager
) : Listener {

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        event.player.run {
            val user = userManager.getOnlineUser(uniqueId) ?: return
            userRepository.saveUser(user)
            permissionsManager.removePermissions(this)
            setDisplayName(null)
            setPlayerListName(null)
        }
    }

}