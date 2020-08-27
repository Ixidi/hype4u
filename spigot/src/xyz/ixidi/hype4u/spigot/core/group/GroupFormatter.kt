package xyz.ixidi.hype4u.spigot.core.group

import org.bukkit.entity.Player
import xyz.ixidi.hype4u.spigot.core.group.Group
import xyz.ixidi.hype4u.spigot.core.user.User
import xyz.ixidi.hype4u.spigot.framework.util.extension.color
import xyz.ixidi.hype4u.spigot.framework.util.extension.message
import xyz.ixidi.hype4u.spigot.misc.Permission

object GroupFormatter {

    fun formatDisplayName(player: Player, user: User): String {
        val group = user.userGroups.primaryGroup()
        return group.displayNameFormat.replace("{name}", player.displayName).color()
    }

    fun formatChat(player: Player, user: User, message: String): String {
        val group = user.userGroups.primaryGroup()
        var format = group.chatFormat
            .replace("{name}", player.name)
            .replace("{dname}", player.displayName)

        format = if (player.hasPermission(Permission.CHAT_COLORED_MESSAGE)) {
            format.replace("{message}", message).color()
        } else {
            format.color().replace("{message}", message)
        }

        return format
    }
    
}