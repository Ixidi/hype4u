package xyz.ixidi.hype4u.spigot.core.database.punishment

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PunishmentEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<PunishmentEntity>(PunishmentTable)

    var active by PunishmentTable.active
    var type by PunishmentTable.type
    var target by PunishmentTable.target
    var targetUUID by PunishmentTable.targetUUID
    var executor by PunishmentTable.executor
    var executorUUID by PunishmentTable.executorUUID
    var date by PunishmentTable.date
    var reason by PunishmentTable.reason
    var expires by PunishmentTable.expires

}