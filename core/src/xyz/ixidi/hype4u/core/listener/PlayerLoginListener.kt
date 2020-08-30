package xyz.ixidi.hype4u.core.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.punishment.PunishmentType
import xyz.ixidi.hype4u.core.repository.punishment.PunishmentRepository
import xyz.ixidi.hype4u.framework.message.Messages

class PlayerLoginListener(
    private val punishmentRepository: PunishmentRepository,
    private val messages: Messages
) : Listener {

    @EventHandler
    fun onPlayerLogin(event: PlayerLoginEvent) {
        val punishments = punishmentRepository.getActivePunishmentsByTarget(event.player.uniqueId)
        if (punishments.isEmpty()) {
            return
        }

        val active = punishments.firstOrNull { it.active && it.type == PunishmentType.PERMANENT_BAN }
        if (active != null) {
            val r = if (active.reason.isNotBlank()) messages.getColoredMessage(
                CoreTranslatableKey.MESSAGE_COMMAND_BAN_SCREEN_REASON,
                "reason" to active.reason,
                "name" to active.executor
            ) else messages.getColoredMessage(CoreTranslatableKey.MESSAGE_COMMAND_BAN_SCREEN)

            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, r)
        }
    }

}