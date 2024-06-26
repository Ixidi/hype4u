package xyz.ixidi.hype4u.core.feature.punishment

import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.ixidi.hype4u.core.misc.CoreTranslatableKey
import xyz.ixidi.hype4u.core.repository.punishment.PunishmentRepository
import xyz.ixidi.hype4u.framework.message.Messages
import xyz.ixidi.hype4u.core.misc.Permission
import xyz.ixidi.hype4u.framework.task.TaskManager
import xyz.ixidi.hype4u.framework.util.DateFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class PunishmentManagerImpl(
    private val punishmentRepository: PunishmentRepository,
    private val messages: Messages,
    private val server: Server,
    private val taskManager: TaskManager
) : PunishmentManager {

    class PlayerBypassPermissionException : Exception()

    private val punishmentsMap = HashMap<UUID, MutableList<Pair<Punishment, Boolean>>>()

    override fun getPunishments(target: UUID): List<Punishment> {
        var punishments = punishmentsMap[target]
        if (punishments == null) {
            punishments = ArrayList()
            punishmentsMap[target] = punishments
        }

        return punishments.map { it.first }
    }

    override fun getActivePunishments(target: UUID): List<Punishment> = getPunishments(target).filter { it.active }

    override fun load(uuid: UUID) {
        punishmentsMap[uuid] =
            punishmentRepository.getActivePunishmentsByTarget(uuid).map { it to false }.toMutableList()
    }

    override fun save(uuid: UUID) {
        val punishments = punishmentsMap[uuid] ?: return
        punishments.filter { it.second }.forEach {
            punishmentRepository.savePunishment(it.first)
        }
    }

    private fun addPunishment(uuid: UUID, punishment: Punishment) {
        if (punishmentsMap.containsKey(uuid)) {
            punishmentsMap[uuid]!!.add(punishment to true)
        } else {
            punishmentsMap[uuid] = arrayListOf(punishment to true)
        }
    }

    override fun kick(player: Player, executor: CommandSender, reason: String) {
        if (player.hasPermission(Permission.KICK_BYPASS)) {
            throw PlayerBypassPermissionException()
        }

        val r = if (reason.isNotBlank()) messages.getColoredMessage(
            CoreTranslatableKey.MESSAGE_COMMAND_KICK_SCREEN_REASON,
            "reason" to reason
        ) else messages.getColoredMessage(CoreTranslatableKey.MESSAGE_COMMAND_KICK_SCREEN)

        val executorUUID = if (executor is Player) executor.uniqueId else UUID.nameUUIDFromBytes(executor.name.toByteArray())

        addPunishment(
            player.uniqueId,
            Punishment(
                active = false,
                type = PunishmentType.KICK,
                target = player.name,
                targetUUID = player.uniqueId,
                executor = executor.name,
                executorUUID = executorUUID,
                date = Date(),
                reason = reason,
                expiresAt = null
            )
        )

        player.kickPlayer(r)
    }

    override fun permanentBan(uuid: UUID, name: String, executor: CommandSender, reason: String) {
        val executorUUID = if (executor is Player) executor.uniqueId else UUID.nameUUIDFromBytes(executor.name.toByteArray())
        val punishment = Punishment(
            active = true,
            type = PunishmentType.PERMANENT_BAN,
            target = name,
            targetUUID = uuid,
            executor = executor.name,
            executorUUID = executorUUID,
            date = Date(),
            reason = reason,
            expiresAt = null
        )

        taskManager.runAsyncTask {
            punishmentRepository.savePunishment(punishment)
        }

        server.getPlayer(uuid)?.let {
            val r = if (reason.isNotBlank()) messages.getColoredMessage(
                CoreTranslatableKey.MESSAGE_COMMAND_BAN_SCREEN_REASON,
                "reason" to reason,
                "name" to executor.name
            ) else messages.getColoredMessage(CoreTranslatableKey.MESSAGE_COMMAND_BAN_SCREEN, "name" to executor.name)

            it.kickPlayer(r)
        }

    }

    override fun temporaryBan(uuid: UUID, name: String, executor: CommandSender, reason: String, expiresAt: Date) {
        val executorUUID = if (executor is Player) executor.uniqueId else UUID.nameUUIDFromBytes(executor.name.toByteArray())
        val punishment = Punishment(
            active = true,
            type = PunishmentType.TEMPORARY_BAN,
            target = name,
            targetUUID = uuid,
            executor = executor.name,
            executorUUID = executorUUID,
            date = Date(),
            reason = reason,
            expiresAt = expiresAt
        )

        taskManager.runAsyncTask {
            punishmentRepository.savePunishment(punishment)
        }

        server.getPlayer(uuid)?.let {
            val r = if (reason.isNotBlank()) messages.getColoredMessage(
                CoreTranslatableKey.MESSAGE_COMMAND_TEMPBAN_SCREEN_REASON,
                "reason" to reason,
                "expires" to DateFormatter.formatDate(expiresAt),
                "name" to executor.name
            ) else messages.getColoredMessage(CoreTranslatableKey.MESSAGE_COMMAND_TEMPBAN_SCREEN, "name" to executor.name)

            it.kickPlayer(r)
        }

    }

    override fun unban(uuid: UUID, name: String, executor: CommandSender, reason: String) {
        punishmentRepository.inactivePunishments(uuid, PunishmentType.PERMANENT_BAN, PunishmentType.TEMPORARY_BAN)

        val executorUUID = if (executor is Player) executor.uniqueId else UUID.nameUUIDFromBytes(executor.name.toByteArray())
        val punishment = Punishment(
            active = true,
            type = PunishmentType.UNBAN,
            target = name,
            targetUUID = uuid,
            executor = executor.name,
            executorUUID = executorUUID,
            date = Date(),
            reason = reason,
            expiresAt = null
        )

        taskManager.runAsyncTask {
            punishmentRepository.savePunishment(punishment)
        }
    }


}