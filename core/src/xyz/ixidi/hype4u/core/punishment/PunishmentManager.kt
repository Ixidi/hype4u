package xyz.ixidi.hype4u.core.punishment

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

interface PunishmentManager {

    fun getPunishments(target: UUID): List<Punishment>
    fun getActivePunishments(target: UUID): List<Punishment>

    fun load(uuid: UUID)
    fun save(uuid: UUID)

    fun kick(player: Player, executor: CommandSender, reason: String)
    fun permanentBan(uuid: UUID, name: String, executor: CommandSender, reason: String)
    fun temporaryBan(uuid: UUID, executor: CommandSender, reason: String, expiresAt: Date)
    fun revokeBan(uuid: UUID, executor: CommandSender, reason: String)

}