package xyz.ixidi.hype4u.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.feature.punishment.PunishmentType
import xyz.ixidi.hype4u.core.repository.punishment.PunishmentRepository
import xyz.ixidi.hype4u.core.repository.user.UserRepository
import xyz.ixidi.hype4u.core.user.UserManager
import xyz.ixidi.hype4u.framework.message.Messages
import xyz.ixidi.hype4u.framework.util.DateFormatter

class AsyncPlayerPreLoginListener(
    private val punishmentRepository: PunishmentRepository,
    private val userManager: UserManager,
    private val userRepository: UserRepository,
    private val messages: Messages
) : Listener {

    @EventHandler
    fun onAsyncPlayerPreLogin(event: AsyncPlayerPreLoginEvent) {
        val punishments = punishmentRepository.getActivePunishmentsByTarget(event.uniqueId)
        if (punishments.isNotEmpty()) {
            val active = punishments.firstOrNull { it.active }
            if (active != null) {
                when (active.type) {
                    PunishmentType.PERMANENT_BAN -> {
                        val r = if (active.reason.isNotBlank()) messages.getColoredMessage(
                            CoreTranslatableKey.MESSAGE_COMMAND_BAN_SCREEN_REASON,
                            "reason" to active.reason,
                            "name" to active.executor
                        ) else messages.getColoredMessage(CoreTranslatableKey.MESSAGE_COMMAND_BAN_SCREEN)

                        event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, r)
                        return
                    }

                    PunishmentType.TEMPORARY_BAN -> {
                        val expires = active.expiresAt!!
                        if (System.currentTimeMillis() >= expires.time) {
                            punishmentRepository.inactivePunishments(event.uniqueId, PunishmentType.TEMPORARY_BAN)
                        } else {
                            val r = if (active.reason.isNotBlank()) messages.getColoredMessage(
                                CoreTranslatableKey.MESSAGE_COMMAND_TEMPBAN_SCREEN_REASON,
                                "reason" to active.reason,
                                "expires" to DateFormatter.formatDate(expires),
                                "name" to active.executor
                            ) else messages.getColoredMessage(CoreTranslatableKey.MESSAGE_COMMAND_BAN_SCREEN)

                            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, r)
                            return
                        }
                    }
                }
            }
        }

        val user = userRepository.loadUser(event.uniqueId)
        userManager.addOnlineUser(user)
    }

}