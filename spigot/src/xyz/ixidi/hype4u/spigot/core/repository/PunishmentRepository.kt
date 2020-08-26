package xyz.ixidi.hype4u.spigot.core.repository

import xyz.ixidi.hype4u.spigot.core.punishment.Punishment
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentType
import java.util.*

interface PunishmentRepository {

    fun getPunishments(): List<Punishment>
    fun getPunishmentsByTarget(target: UUID): List<Punishment>
    fun getActivePunishmentsByTarget(target: UUID): List<Punishment>
    fun getPunishmentsByExecutor(executor: UUID): List<Punishment>
    fun getPunishmentsByTarget(target: String): List<Punishment>
    fun getActivePunishmentsByTarget(target: String): List<Punishment>
    fun getPunishmentsByExecutor(executor: String): List<Punishment>

    fun inactivePunishments(vararg type: PunishmentType)
    fun savePunishment(punishment: Punishment)
}