package xyz.ixidi.hype4u.core.repository.punishment

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import xyz.ixidi.hype4u.core.feature.punishment.Punishment
import xyz.ixidi.hype4u.core.feature.punishment.PunishmentType
import xyz.ixidi.hype4u.core.database.punishment.PunishmentEntity
import xyz.ixidi.hype4u.core.database.punishment.PunishmentEntityMapper
import xyz.ixidi.hype4u.core.database.punishment.PunishmentTable
import xyz.ixidi.hype4u.framework.util.map
import java.util.*

class DatabasePunishmentRepository(
    private val database: Database
) : PunishmentRepository {

    override fun getPunishments(): List<Punishment> = transaction(database) {
        PunishmentEntity.all().map { it.map(PunishmentEntityMapper) }
    }

    override fun getPunishmentsByTarget(target: UUID): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.targetUUID eq target.toString() }.map { it.map(PunishmentEntityMapper) }
    }

    override fun getActivePunishmentsByTarget(target: UUID): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.targetUUID.eq(target.toString()) and PunishmentTable.active.eq(true) }
            .map { it.map(PunishmentEntityMapper) }
    }

    override fun getPunishmentsByExecutor(executor: UUID): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.executorUUID eq executor.toString() }.map { it.map(PunishmentEntityMapper) }
    }

    override fun getPunishmentsByTarget(target: String): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.target eq target }.map { it.map(PunishmentEntityMapper) }
    }

    override fun getActivePunishmentsByTarget(target: String): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.target.eq(target) and PunishmentTable.active.eq(true) }
            .map { it.map(PunishmentEntityMapper) }
    }

    override fun getPunishmentsByExecutor(executor: String): List<Punishment> = transaction(database) {
        PunishmentEntity.find { PunishmentTable.executor.eq(executor) }
            .map { it.map(PunishmentEntityMapper) }
    }

    override fun inactivePunishments(uuid: UUID, vararg type: PunishmentType) {
        transaction(database) {
            PunishmentEntity.find {
                PunishmentTable.targetUUID.eq(uuid.toString()) and PunishmentTable.active.eq(true) and PunishmentTable.type.inList(type.map { it.typeName })
            }.forEach { it.active = false }
        }
    }

    override fun savePunishment(punishment: Punishment) {
        transaction(database) {
            if (punishment.type == PunishmentType.PERMANENT_BAN && punishment.type == PunishmentType.TEMPORARY_BAN) {
                inactivePunishments(punishment.targetUUID, PunishmentType.PERMANENT_BAN, PunishmentType.TEMPORARY_BAN)
            }
            PunishmentEntity.new {
                active = punishment.active
                type = punishment.type.typeName
                target = punishment.target
                targetUUID = punishment.targetUUID.toString()
                executor = punishment.executor
                executorUUID = punishment.executorUUID.toString()
                date = punishment.date.time
                reason = punishment.reason
                expires = punishment.expiresAt?.time
            }
        }
    }
}