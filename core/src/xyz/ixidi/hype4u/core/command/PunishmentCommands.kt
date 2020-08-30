package xyz.ixidi.hype4u.core.command

import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.config.PluginConfig
import xyz.ixidi.hype4u.core.punishment.PunishmentManager
import xyz.ixidi.hype4u.core.punishment.PunishmentManagerImpl
import xyz.ixidi.hype4u.framework.command.annotation.*
import xyz.ixidi.hype4u.framework.util.extension.message
import xyz.ixidi.hype4u.core.misc.Permission

@SingleCommandsContainer
class PunishmentCommands(
    private val config: PluginConfig,
    private val server: Server,
    private val punishmentManager: PunishmentManager
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
        try {
            punishmentManager.kick(target, sender, reason)
        } catch (ex: PunishmentManagerImpl.PlayerBypassPermissionException) {
            sender.message(CoreTranslatableKey.MESSAGE_COMMAND_KICK_BYPASS)
            return
        }

        if (config.kickBroadcast) server.onlinePlayers.forEach { it.message(CoreTranslatableKey.MESSAGE_COMMAND_KICK_BROADCAST, "target" to target.name) }
    }

    @Command(
        name = "ban",
        desc = "Blokuje dostęp do serwera.",
        permission = Permission.COMMAND_BAN
    )
    private fun ban(
        @Sender sender: CommandSender,
        @Arg("cel", "Gracz, który ma zostać zablokowany.") target: Player,
        @Arg("powód", "Powód blokady.", " ") @GreedyString reason: String
    ) {
        try {
            punishmentManager.permanentBan(target.uniqueId, target.name, sender, reason)
        } catch (ex: PunishmentManagerImpl.PlayerBypassPermissionException) {
            sender.message(CoreTranslatableKey.MESSAGE_COMMAND_BAN_BYPASS)
            return
        }

        if (config.banBroadcast) server.onlinePlayers.forEach { it.message(CoreTranslatableKey.MESSAGE_COMMAND_BAN_BROADCAST, "target" to target.name) }
    }


}