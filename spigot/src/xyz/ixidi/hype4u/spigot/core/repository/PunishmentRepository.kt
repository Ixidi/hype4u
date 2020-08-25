package xyz.ixidi.hype4u.spigot.core.repository

import xyz.ixidi.hype4u.spigot.core.punishment.Punishment
import java.util.*

interface PunishmentRepository {

    fun getPunishments(): List<Punishment>
    fun getPunishmentsByTarget(target: UUID): List<Punishment>
    fun getActivePunishmentsByTarget(target: UUID): List<Punishment>
    fun getPunishmentsByExecutor(executor: UUID): List<Punishment>

    fun savePunishment(punishment: Punishment)
}