package xyz.ixidi.hype4u.core.repository.punishment

import xyz.ixidi.hype4u.core.feature.punishment.Punishment
import xyz.ixidi.hype4u.core.feature.punishment.PunishmentType
import java.util.*

interface PunishmentRepository {

    fun getPunishments(): List<Punishment>
    fun getPunishmentsByTarget(target: UUID): List<Punishment>
    fun getActivePunishmentsByTarget(target: UUID): List<Punishment>
    fun getPunishmentsByExecutor(executor: UUID): List<Punishment>
    fun getPunishmentsByTarget(target: String): List<Punishment>
    fun getActivePunishmentsByTarget(target: String): List<Punishment>
    fun getPunishmentsByExecutor(executor: String): List<Punishment>

    fun inactivePunishments(uuid: UUID, vararg type: PunishmentType)
    fun savePunishment(punishment: Punishment)

}