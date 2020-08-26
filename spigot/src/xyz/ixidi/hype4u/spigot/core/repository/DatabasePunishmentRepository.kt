package xyz.ixidi.hype4u.spigot.core.repository

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import xyz.ixidi.hype4u.spigot.core.punishment.Punishment
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentType
import xyz.ixidi.hype4u.spigot.core.database.punishment.PunishmentEntity
import xyz.ixidi.hype4u.spigot.core.database.punishment.PunishmentEntityMapper
import xyz.ixidi.hype4u.spigot.core.database.punishment.PunishmentTable
import xyz.ixidi.hype4u.spigot.framework.util.map
import java.util.*

class DatabasePunishmentRepository(
    private val database: Database
) : PunishmentRepository {

    override fun getPunishments(): List<Punishment> = transaction(database) {
        PunishmentEntity.all().map { it.map(PunishmentEntityMapper) }
    }

    override fun getPunishmentsByTarget(target: UUID): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.targetUUID eq target }.map { it.map(PunishmentEntityMapper) }
    }

    override fun getActivePunishmentsByTarget(target: UUID): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.targetUUID.eq(target) and PunishmentTable.active.eq(true) }
            .map { it.map(PunishmentEntityMapper) }
    }

    override fun getPunishmentsByExecutor(executor: UUID): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.executorUUID eq executor }.map { it.map(PunishmentEntityMapper) }
    }

    override fun getPunishmentsByTarget(target: String): List<Punishment> {
        TODO("Not yet implemented")
    }

    override fun getActivePunishmentsByTarget(target: String): List<Punishment> {
        TODO("Not yet implemented")
    }

    override fun getPunishmentsByExecutor(executor: String): List<Punishment> {
        TODO("Not yet implemented")
    }

    override fun inactivePunishments(vararg type: PunishmentType) {
        transaction(database) {
            PunishmentEntity.find {
                PunishmentTable.active.eq(true) and PunishmentTable.type.inList(type.toList())
            }.forEach { it.active = false }
        }
    }

    override fun savePunishment(punishment: Punishment) {
        transaction(database) {
            if (punishment.type == PunishmentType.PERMANENT_BAN && punishment.type == PunishmentType.TEMPORARY_BAN) {
                inactivePunishments(PunishmentType.PERMANENT_BAN, PunishmentType.TEMPORARY_BAN)
            }
            PunishmentEntity.new {
                active = punishment.active
                type = punishment.type
                target = punishment.target
                targetUUID = punishment.targetUUID
                executor = punishment.executor
                executorUUID = punishment.executorUUID
                date = punishment.date.time
                reason = punishment.reason
                expires = punishment.expiresAt?.time
            }
        }
    }
}