package xyz.ixidi.hype4u.spigot.core.database.punishment

import org.jetbrains.exposed.dao.id.IntIdTable
import xyz.ixidi.hype4u.spigot.core.punishment.PunishmentType

object PunishmentTable : IntIdTable("punishments") {

    val active = bool("active")
    val type = varchar("type", 30)
    val target = varchar("target", 16)
    val targetUUID = varchar("target_uuid", 36)
    val executor = varchar("executor", 16)
    val executorUUID = varchar("executor_uuid", 36)
    val date = long("date")
    val reason = varchar("reason", 200)
    val expires = long("expires").nullable()

}