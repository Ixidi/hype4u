package xyz.ixidi.hype4u.spigot.core.punishment

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.spigot.core.repository.PunishmentRepository
import xyz.ixidi.hype4u.spigot.framework.message.Messages
import xyz.ixidi.hype4u.spigot.misc.Permission
import xyz.ixidi.hype4u.spigot.misc.TranslatableKey
import java.util.*

class PunishmentManagerImpl(
    private val punishmentRepository: PunishmentRepository,
    private val messages: Messages
) : PunishmentManager {

    class PlayerBypassPermissionException : Exception()

    override fun getPunishments(): List<Punishment> = punishmentRepository.getPunishments()
    override fun getPunishmentsByTarget(target: UUID): List<Punishment> = punishmentRepository.getPunishmentsByTarget(target)
    override fun getActivePunishmentsByTarget(target: UUID): List<Punishment> = getActivePunishmentsByTarget(target)
    override fun getPunishmentsByExecutor(executor: UUID): List<Punishment> = getPunishmentsByExecutor(executor)

    override fun getPunishmentsByTarget(target: String): List<Punishment> = punishmentRepository.getPunishmentsByTarget(target)
    override fun getActivePunishmentsByTarget(target: String): List<Punishment> = punishmentRepository.getActivePunishmentsByTarget(target)
    override fun getPunishmentsByExecutor(executor: String): List<Punishment> = punishmentRepository.getPunishmentsByExecutor(executor)

    override fun kick(player: Player, executor: CommandSender, reason: String) {
        if (player.hasPermission(Permission.KICK_BYPASS)) {
            throw PlayerBypassPermissionException()
        }

        val r = if (reason.isNotBlank()) messages.getColoredMessage(
            TranslatableKey.MESSAGE_COMMAND_KICK_SCREEN_REASON,
            "reason" to reason
        ) else messages.getColoredMessage(TranslatableKey.MESSAGE_COMMAND_KICK_SCREEN)
        player.kickPlayer(r)

        val executorUUID = if (executor is Player) executor.uniqueId else UUID.nameUUIDFromBytes(executor.name.toByteArray())
        punishmentRepository.savePunishment(
            Punishment(
                false, PunishmentType.KICK, player.name, player.uniqueId, executor.name, executorUUID, Date(), reason, null
            )
        )
    }

    override fun permanentBan(uuid: UUID, executor: CommandSender, reason: String) {
        TODO("Not yet implemented")
    }

    override fun temporaryBan(player: Player, executor: CommandSender, reason: String, expiresAt: Date) {
        TODO("Not yet implemented")
    }

    override fun revokeBan(uuid: UUID, executor: CommandSender, reason: String) {
        TODO("Not yet implemented")
    }


}