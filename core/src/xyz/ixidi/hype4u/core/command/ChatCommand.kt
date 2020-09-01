package xyz.ixidi.hype4u.core.command

import org.bukkit.Server
import org.bukkit.command.CommandSender
import xyz.ixidi.hype4u.core.feature.chat.ChatManager
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.misc.Permission
import xyz.ixidi.hype4u.framework.command.annotation.Command
import xyz.ixidi.hype4u.framework.command.annotation.Sender
import xyz.ixidi.hype4u.framework.command.annotation.SubCommandsContainer
import xyz.ixidi.hype4u.framework.util.extension.message

@SubCommandsContainer(
    Command(
        name = "chat",
        desc = "Zarządzanie chatem."
    )
)
class ChatCommand(
    private val chatManager: ChatManager,
    private val server: Server
) {

    @Command(
        name = "clear",
        desc = "Czyszczenie chatu.",
        aliases = ["c"],
        permission = Permission.CHAT_CLEAR
    )
    private fun clear(@Sender sender: CommandSender) {
        chatManager.clear()
        server.onlinePlayers.forEach { it.message(CoreTranslatableKey.MESSAGE_CHAT_CLEARED_BROADCAST) }
    }

    @Command(
        name = "enable",
        desc = "Włączenie chatu.",
        aliases = ["on"],
        permission = Permission.CHAT_SWAP_STATE
    )
    private fun enable(@Sender sender: CommandSender) {
        if (chatManager.isEnabled()) {
            sender.message(CoreTranslatableKey.MESSAGE_CHAT_ALREADY_ENABLED)
            return
        }
        chatManager.enable()

        server.onlinePlayers.forEach { it.message(CoreTranslatableKey.MESSAGE_CHAT_ENABLED_BROADCAST) }
    }

    @Command(
        name = "disable",
        desc = "Wyłączenie chatu.",
        aliases = ["off"],
        permission = Permission.CHAT_SWAP_STATE
    )
    private fun disable(@Sender sender: CommandSender) {
        if (!chatManager.isEnabled()) {
            sender.message(CoreTranslatableKey.MESSAGE_CHAT_ALREADY_DISABLED)
            return
        }
        chatManager.disable()

        server.onlinePlayers.forEach { it.message(CoreTranslatableKey.MESSAGE_CHAT_DISABLED_BROADCAST) }
    }

    @Command(
        name = "swapstate",
        desc = "Wyłączenie/Włączenie chatu.",
        permission = Permission.CHAT_SWAP_STATE
    )
    private fun swapstate(@Sender sender: CommandSender) {
        chatManager.swapState()

        val key =
            if (chatManager.isEnabled()) CoreTranslatableKey.MESSAGE_CHAT_ENABLED_BROADCAST else CoreTranslatableKey.MESSAGE_CHAT_DISABLED_BROADCAST

        server.onlinePlayers.forEach {
            it.message(key)
        }
    }

}