package xyz.ixidi.hype4u.spigot.database.punishment

import org.jetbrains.exposed.dao.id.IntIdTable
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentType

object PunishmentTable : IntIdTable("punishments") {

    val active = bool("active")
    val type = enumeration("type", PunishmentType::class)
    val target = varchar("target", 16)
    val targetUUID = uuid("target_uuid")
    val executor = varchar("executor", 16)
    val executorUUID = uuid("executor_uuid")
    val date = long("date")
    val reason = varchar("reason", 200)
    val expires = long("expires").nullable()

}