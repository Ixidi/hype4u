package xyz.ixidi.hype4u.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import xyz.ixidi.hype4u.core.feature.chat.ChatManager
import xyz.ixidi.hype4u.core.feature.group.GroupFormatter
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.misc.Permission
import xyz.ixidi.hype4u.core.user.UserManager
import xyz.ixidi.hype4u.framework.util.extension.message

class PlayerChatListener(
    private val userManager: UserManager,
    private val chatManager: ChatManager
) : Listener {

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        if (!chatManager.isEnabled() && !event.player.hasPermission(Permission.CHAT_DISABLE_BYPASS)) {
            event.player.message(CoreTranslatableKey.MESSAGE_CHAT_DISABLED)
            event.isCancelled = true
            return
        }
        val player = event.player
        val user = userManager.getOnlineUser(player)
        val message = event.message

        event.format = GroupFormatter.formatChat(player, user, message)
    }

}