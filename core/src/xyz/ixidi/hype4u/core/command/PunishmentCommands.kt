package xyz.ixidi.hype4u.core.command

import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.config.PluginConfig
import xyz.ixidi.hype4u.core.feature.punishment.PunishmentManager
import xyz.ixidi.hype4u.core.feature.punishment.PunishmentManagerImpl
import xyz.ixidi.hype4u.framework.command.annotation.*
import xyz.ixidi.hype4u.framework.util.extension.message
import xyz.ixidi.hype4u.core.misc.Permission
import xyz.ixidi.hype4u.core.repository.punishment.PunishmentRepository
import xyz.ixidi.hype4u.core.repository.userinfo.UserInfo
import xyz.ixidi.hype4u.core.repository.userinfo.UserInfoRepository
import xyz.ixidi.hype4u.framework.task.TaskManager
import xyz.ixidi.hype4u.framework.util.DateFormatter
import java.util.*

@SingleCommandsContainer
class PunishmentCommands(
    private val config: PluginConfig,
    private val server: Server,
    private val punishmentManager: PunishmentManager,
    private val punishmentRepository: PunishmentRepository,
    private val taskManager: TaskManager,
    private val userInfoRepository: UserInfoRepository
) {

    private class PlayerNotBannedException : Exception()
    private class PlayerHaveNotPlayedException : Exception()

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

        if (config.kickBroadcast) server.onlinePlayers.forEach {
            it.message(
                CoreTranslatableKey.MESSAGE_COMMAND_KICK_BROADCAST,
                "target" to target.name
            )
        }
    }

    @Command(
        name = "ban",
        desc = "Permanentnie blokuje dostęp do serwera.",
        permission = Permission.COMMAND_BAN
    )
    private fun ban(
        @Sender sender: CommandSender,
        @Arg("cel", "Gracz, który ma zostać zablokowany, lub jego uuid.") target: String,
        @Arg("powód", "Powód blokady.", " ") @GreedyString reason: String
    ) {
        val player = server.getPlayer(target)

        taskManager.runAsyncWithSyncCallback(
            run = {
                if (player != null) {
                    UserInfo(player.name, player.uniqueId)
                } else {
                    userInfoRepository.loadUserInfo(target) ?: throw PlayerHaveNotPlayedException()
                }
            },
            callback = {
                try {
                    punishmentManager.permanentBan(it.uuid, it.name, sender, reason)
                } catch (exception: PunishmentManagerImpl.PlayerBypassPermissionException) {
                    sender.message(CoreTranslatableKey.MESSAGE_COMMAND_BAN_BYPASS)
                    return@runAsyncWithSyncCallback
                }

                if (config.banBroadcast) server.onlinePlayers.forEach { p ->
                    p.message(
                        CoreTranslatableKey.MESSAGE_COMMAND_BAN_BROADCAST,
                        "target" to it
                    )
                }

                sender.message(CoreTranslatableKey.MESSAGE_COMMAND_BAN_BANNED, "target" to it.name)
            },
            error = {
                when (it) {
                    is PunishmentManagerImpl.PlayerBypassPermissionException -> sender.message(CoreTranslatableKey.MESSAGE_COMMAND_BAN_BYPASS)
                    is PlayerHaveNotPlayedException -> sender.message(
                        CoreTranslatableKey.MESSAGE_PLAYER_NEVER_PLAYED,
                        "name" to target
                    )
                }
            }
        )
    }

    @Command(
        name = "tempban",
        desc = "Czasowo blokuje dostęp do serwera.",
        permission = Permission.COMMAND_BAN
    )
    private fun tempban(
        @Sender sender: CommandSender,
        @Arg("cel", "Gracz, który ma zostać zablokowany.") target: String,
        @Arg("czas", "Czas trwania bana.") expires: Date,
        @Arg("powód", "Powód blokady.", " ") @GreedyString reason: String
    ) {
        val player = server.getPlayer(target)

        taskManager.runAsyncWithSyncCallback(
            run = {
                if (player != null) {
                    UserInfo(player.name, player.uniqueId)
                } else {
                    userInfoRepository.loadUserInfo(target) ?: throw PlayerHaveNotPlayedException()
                }
            },
            callback = {
                try {
                    punishmentManager.temporaryBan(it.uuid, it.name, sender, reason, expires)
                } catch (exception: PunishmentManagerImpl.PlayerBypassPermissionException) {
                    sender.message(CoreTranslatableKey.MESSAGE_COMMAND_TEMPBAN_BYPASS)
                    return@runAsyncWithSyncCallback
                }

                if (config.banBroadcast) server.onlinePlayers.forEach { p ->
                    p.message(
                        CoreTranslatableKey.MESSAGE_COMMAND_TEMPBAN_BROADCAST,
                        "target" to it
                    )
                }

                sender.message(CoreTranslatableKey.MESSAGE_COMMAND_TEMPBAN_BANNED, "target" to it.name, "expires" to DateFormatter.formatDate(expires))
            },
            error = {
                when (it) {
                    is PunishmentManagerImpl.PlayerBypassPermissionException -> sender.message(CoreTranslatableKey.MESSAGE_COMMAND_TEMPBAN_BYPASS)
                    is PlayerHaveNotPlayedException -> sender.message(
                        CoreTranslatableKey.MESSAGE_PLAYER_NEVER_PLAYED,
                        "name" to target
                    )
                }
            }
        )
    }

    @Command(
        name = "unban",
        desc = "Zdejmuje wszystkie blokady konta.",
        permission = Permission.COMMAND_UNBAN
    )
    private fun unban(
        @Sender sender: CommandSender,
        @Arg("cel", "Gracz, który ma zostać zablokowany.") target: String,
        @Arg("powód", "Powód zdjęcia blokady.", " ") @GreedyString reason: String
    ) {
        taskManager.runAsyncWithSyncCallback(
            run = {
                val punishments = punishmentRepository.getActivePunishmentsByTarget(target)
                if (punishments.isEmpty()) throw PlayerNotBannedException()

                punishmentManager.unban(punishments[0].targetUUID, punishments[0].target, sender, reason)
                punishments[0].target
            },
            callback = {
                sender.message(CoreTranslatableKey.MESSAGE_COMMAND_UNBAN_UNBANNED, "target" to it)
            },
            error = {
                when (it) {
                    is PlayerNotBannedException -> sender.message(CoreTranslatableKey.MESSAGE_COMMAND_UNBAN_NOT_BANNED, "name" to target)
                }
            }
        )
    }


}