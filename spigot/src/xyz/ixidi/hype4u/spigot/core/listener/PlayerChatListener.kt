package xyz.ixidi.hype4u.spigot.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import xyz.ixidi.hype4u.spigot.core.group.GroupFormatter
import xyz.ixidi.hype4u.spigot.core.user.UserManager

class PlayerChatListener(
    private val userManager: UserManager
) : Listener {

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        val player = event.player
        val user = userManager.getOnlineUser(player)
        val message = event.message

        event.format = GroupFormatter.formatChat(player, user, message)
    }

}