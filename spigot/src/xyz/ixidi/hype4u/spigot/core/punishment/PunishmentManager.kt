package xyz.ixidi.hype4u.spigot.core.punishment

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

interface PunishmentManager {

    fun getPunishments(): List<Punishment>
    fun getPunishmentsByTarget(target: UUID): List<Punishment>
    fun getActivePunishmentsByTarget(target: UUID): List<Punishment>
    fun getPunishmentsByExecutor(executor: UUID): List<Punishment>
    fun getPunishmentsByTarget(target: String): List<Punishment>
    fun getActivePunishmentsByTarget(target: String): List<Punishment>
    fun getPunishmentsByExecutor(executor: String): List<Punishment>

    fun kick(player: Player, executor: CommandSender, reason: String)
    fun permanentBan(uuid: UUID, executor: CommandSender, reason: String)
    fun temporaryBan(player: Player, executor: CommandSender, reason: String, expiresAt: Date)
    fun revokeBan(uuid: UUID, executor: CommandSender, reason: String)

}