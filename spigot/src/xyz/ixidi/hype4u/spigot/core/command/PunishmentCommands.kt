package xyz.ixidi.hype4u.spigot.core.command

import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.spigot.config.PluginConfig
import xyz.ixidi.hype4u.spigot.framework.command.annotation.*
import xyz.ixidi.hype4u.spigot.framework.message.Messages
import xyz.ixidi.hype4u.spigot.framework.util.extension.message
import xyz.ixidi.hype4u.spigot.misc.Permission
import xyz.ixidi.hype4u.spigot.misc.TranslatableKey

@SingleCommandsContainer
class PunishmentCommands(
    private val config: PluginConfig,
    private val messages: Messages,
    private val server: Server
) {

    @Command(
        name = "kick",
        desc = "Wyrzuca gracza z serwera.",
        permission = Permission.COMMAND_KICK
    )
    private fun kick(
        @Sender sender: CommandSender,
        @Arg("cel", "Gracz, który ma zostać wyrzucony.") target: Player,
        @Arg("powód", "Powód wyrzucenia.", " ") @GreedyString reason: String
    ) {
        if (target.hasPermission(Permission.KICK_BYPASS)) {
            sender.message(TranslatableKey.MESSAGE_COMMAND_KICK_BYPASS)
            return
        }

        val r = if (reason.isNotBlank()) messages.getColoredMessage(
            TranslatableKey.MESSAGE_COMMAND_KICK_SCREEN_REASON,
            "reason" to reason
        ) else messages.getColoredMessage(TranslatableKey.MESSAGE_COMMAND_KICK_SCREEN)
        target.kickPlayer(r)

        if (config.kickBroadcast) server.onlinePlayers.forEach { it.message(TranslatableKey.MESSAGE_COMMAND_KICK_BROADCAST, "target" to target.name) }
    }

}