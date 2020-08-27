package xyz.ixidi.hype4u.spigot.core.punishment

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

interface PunishmentManager {

    fun getPunishments(target: UUID): List<Punishment>
    fun getActivePunishments(target: UUID): List<Punishment>

    fun load(uuid: UUID)
    fun save(uuid: UUID)

    fun kick(player: Player, executor: CommandSender, reason: String)
    fun permanentBan(uuid: UUID, executor: CommandSender, reason: String)
    fun temporaryBan(player: Player, executor: CommandSender, reason: String, expiresAt: Date)
    fun revokeBan(uuid: UUID, executor: CommandSender, reason: String)

}